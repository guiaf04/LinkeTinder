import { Component, OnInit, ViewChild, ElementRef, HostListener, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatTooltipModule } from '@angular/material/tooltip';

interface MatchCandidate {
  id: string;
  name?: string; // Anonymized initially
  title: string;
  company?: string; // For job posts
  location: string;
  skills: string[];
  experience: string;
  description: string;
  matchPercentage: number;
  salary?: string;
  type: 'job' | 'candidate';
  avatar?: string;
}

@Component({
  selector: 'app-discovery',
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatChipsModule,
    MatProgressBarModule,
    MatTooltipModule
  ],
  templateUrl: './discovery.component.html',
  styleUrl: './discovery.component.scss'
})
export class DiscoveryComponent implements OnInit {
  @ViewChild('cardStack') cardStack!: ElementRef;
  
  currentCard = 0;
  matches: MatchCandidate[] = [
    {
      id: '1',
      title: 'Desenvolvedor Angular Sênior',
      company: 'TechCorp',
      location: 'São Paulo, SP',
      skills: ['Angular', 'TypeScript', 'NgRx', 'Material Design'],
      experience: '5+ anos',
      description: 'Buscamos um desenvolvedor Angular experiente para liderar projetos inovadores em nossa equipe de frontend.',
      matchPercentage: 95,
      salary: 'R$ 12.000 - R$ 18.000',
      type: 'job'
    },
    {
      id: '2',
      title: 'Maria Silva',
      location: 'Rio de Janeiro, RJ',
      skills: ['React', 'Node.js', 'JavaScript', 'AWS'],
      experience: '3 anos',
      description: 'Desenvolvedora frontend apaixonada por criar experiências de usuário excepcionais.',
      matchPercentage: 87,
      type: 'candidate',
      avatar: '/assets/avatars/candidate1.jpg'
    },
    {
      id: '3',
      title: 'Product Manager',
      company: 'StartupXYZ',
      location: 'Remoto',
      skills: ['Product Management', 'Agile', 'Data Analysis', 'UX Design'],
      experience: '4+ anos',
      description: 'Vaga para Product Manager em startup de fintech em crescimento exponencial.',
      matchPercentage: 78,
      salary: 'R$ 10.000 - R$ 15.000',
      type: 'job'
    },
    {
      id: '4',
      title: 'João Santos',
      location: 'Belo Horizonte, MG',
      skills: ['Python', 'Django', 'PostgreSQL', 'Docker'],
      experience: '4 anos',
      description: 'Desenvolvedor backend com expertise em sistemas distribuídos e arquitetura de microserviços.',
      matchPercentage: 92,
      type: 'candidate',
      avatar: '/assets/avatars/candidate2.jpg'
    },
    {
      id: '5',
      title: 'UX/UI Designer',
      company: 'DesignStudio',
      location: 'São Paulo, SP',
      skills: ['Figma', 'Adobe XD', 'Prototyping', 'User Research'],
      experience: '3+ anos',
      description: 'Procuramos designer talentoso para criar experiências digitais memoráveis para nossos clientes.',
      matchPercentage: 85,
      salary: 'R$ 8.000 - R$ 12.000',
      type: 'job'
    }
  ];
  
  currentMatch: MatchCandidate | null = null;
  isAnimating = false;
  
  // Touch/Mouse interaction properties
  isDragging = false;
  startX = 0;
  startY = 0;
  currentX = 0;
  currentY = 0;
  cardElement: HTMLElement | null = null;
  
  ngOnInit() {
    this.loadCurrentMatch();
  }
  
  loadCurrentMatch() {
    if (this.currentCard < this.matches.length) {
      this.currentMatch = this.matches[this.currentCard];
    } else {
      this.currentMatch = null; // No more matches
    }
  }
  
  // Touch Events
  onTouchStart(event: TouchEvent) {
    if (this.isAnimating) return;
    
    this.isDragging = true;
    this.startX = event.touches[0].clientX;
    this.startY = event.touches[0].clientY;
    this.cardElement = event.target as HTMLElement;
    
    // Find the card element
    while (this.cardElement && !this.cardElement.classList.contains('match-card')) {
      this.cardElement = this.cardElement.parentElement;
    }
  }
  
  onTouchMove(event: TouchEvent) {
    if (!this.isDragging || this.isAnimating) return;
    
    event.preventDefault();
    this.currentX = event.touches[0].clientX - this.startX;
    this.currentY = event.touches[0].clientY - this.startY;
    
    this.updateCardTransform();
  }
  
  onTouchEnd(event: TouchEvent) {
    if (!this.isDragging || this.isAnimating) return;
    
    this.isDragging = false;
    this.handleSwipeEnd();
  }
  
  // Mouse Events (for desktop)
  onMouseDown(event: MouseEvent) {
    if (this.isAnimating) return;
    
    this.isDragging = true;
    this.startX = event.clientX;
    this.startY = event.clientY;
    this.cardElement = event.target as HTMLElement;
    
    // Find the card element
    while (this.cardElement && !this.cardElement.classList.contains('match-card')) {
      this.cardElement = this.cardElement.parentElement;
    }
  }
  
  @HostListener('document:mousemove', ['$event'])
  onMouseMove(event: MouseEvent) {
    if (!this.isDragging || this.isAnimating) return;
    
    this.currentX = event.clientX - this.startX;
    this.currentY = event.clientY - this.startY;
    
    this.updateCardTransform();
  }
  
  @HostListener('document:mouseup', ['$event'])
  onMouseUp(event: MouseEvent) {
    if (!this.isDragging || this.isAnimating) return;
    
    this.isDragging = false;
    this.handleSwipeEnd();
  }
  
  private updateCardTransform() {
    if (!this.cardElement) return;
    
    const rotation = this.currentX * 0.1; // Subtle rotation
    const opacity = Math.max(0.7, 1 - Math.abs(this.currentX) / 300);
    
    this.cardElement.style.transform = `translate(${this.currentX}px, ${this.currentY}px) rotate(${rotation}deg)`;
    this.cardElement.style.opacity = opacity.toString();
    
    // Show action indicators
    this.updateActionIndicators();
  }
  
  private updateActionIndicators() {
    const threshold = 100;
    
    // Reset all indicators
    this.removeActionClasses();
    
    if (Math.abs(this.currentX) > threshold) {
      if (this.currentX > 0) {
        this.cardElement?.classList.add('swiping-right');
      } else {
        this.cardElement?.classList.add('swiping-left');
      }
    }
    
    if (this.currentY < -threshold) {
      this.cardElement?.classList.add('swiping-up');
    }
  }
  
  private removeActionClasses() {
    this.cardElement?.classList.remove('swiping-left', 'swiping-right', 'swiping-up');
  }
  
  private handleSwipeEnd() {
    const threshold = 100;
    
    if (Math.abs(this.currentX) > threshold) {
      // Horizontal swipe
      if (this.currentX > 0) {
        this.animateCard('right');
      } else {
        this.animateCard('left');
      }
    } else if (this.currentY < -threshold) {
      // Upward swipe
      this.animateCard('up');
    } else {
      // Return to center
      this.resetCard();
    }
  }
  
  private resetCard() {
    if (!this.cardElement) return;
    
    this.cardElement.style.transition = 'all 0.3s ease-out';
    this.cardElement.style.transform = 'translate(0px, 0px) rotate(0deg)';
    this.cardElement.style.opacity = '1';
    this.removeActionClasses();
    
    setTimeout(() => {
      if (this.cardElement) {
        this.cardElement.style.transition = '';
      }
    }, 300);
  }
  
  onSwipeLeft() {
    if (this.isAnimating) return;
    this.animateCard('left');
  }
  
  onSwipeRight() {
    if (this.isAnimating) return;
    this.animateCard('right');
  }
  
  onSuperLike() {
    if (this.isAnimating) return;
    this.animateCard('up');
  }
  
  private animateCard(direction: 'left' | 'right' | 'up') {
    if (!this.cardElement) {
      this.cardElement = document.querySelector('.match-card') as HTMLElement;
    }
    
    if (!this.cardElement) return;
    
    this.isAnimating = true;
    this.removeActionClasses();
    
    const translateX = direction === 'left' ? -1000 : direction === 'right' ? 1000 : 0;
    const translateY = direction === 'up' ? -1000 : 0;
    const rotation = direction === 'left' ? -30 : direction === 'right' ? 30 : 0;
    
    this.cardElement.style.transition = 'all 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.275)';
    this.cardElement.style.transform = `translate(${translateX}px, ${translateY}px) rotate(${rotation}deg)`;
    this.cardElement.style.opacity = '0';
    
    const action = direction === 'left' ? 'rejeitou' : 
                  direction === 'up' ? 'super curtiu' : 'aprovou';
                  
    console.log(`Usuário ${action} ${this.currentMatch?.title}`);
    
    // Show brief feedback
    this.showActionFeedback(action);
    
    setTimeout(() => {
      this.currentCard++;
      this.loadCurrentMatch();
      this.isAnimating = false;
      this.currentX = 0;
      this.currentY = 0;
      this.cardElement = null;
    }, 600);
  }
  
  private showActionFeedback(action: string) {
    // Could show a toast or brief animation here
    console.log(`Feedback: ${action}`);
  }
  
  getSkillColor(skill: string): string {
    const colors = ['primary', 'accent', 'warn'];
    const index = skill.length % colors.length;
    return colors[index];
  }
  
  refreshMatches() {
    this.currentCard = 0;
    this.loadCurrentMatch();
  }
}
