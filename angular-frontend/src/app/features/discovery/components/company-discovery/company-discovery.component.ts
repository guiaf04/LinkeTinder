import { Component, OnInit, ViewChild, ElementRef, HostListener } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatBadgeModule } from '@angular/material/badge';
import { CompanyMatch } from '../../../../core/interfaces/user.interface';

@Component({
  selector: 'app-company-discovery',
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatChipsModule,
    MatProgressBarModule,
    MatTooltipModule,
    MatBadgeModule
  ],
  templateUrl: './company-discovery.component.html',
  styleUrl: './company-discovery.component.scss'
})
export class CompanyDiscoveryComponent implements OnInit {
  @ViewChild('cardStack') cardStack!: ElementRef;
  
  currentCard = 0;
  matches: CompanyMatch[] = [
    {
      id: '1',
      candidateId: 'candidate-1',
      jobId: 'job-1',
      candidate: {
        firstName: 'Ana',
        lastName: 'Silva',
        anonymousId: 'AS_001',
        professionalSummary: 'Desenvolvedora Frontend especializada em Angular e React com 5 anos de experiência. Apaixonada por criar interfaces intuitivas e performáticas.',
        skills: [
          { name: 'Angular', level: 'advanced', yearsOfExperience: 4 },
          { name: 'TypeScript', level: 'advanced', yearsOfExperience: 4 },
          { name: 'React', level: 'intermediate', yearsOfExperience: 2 },
          { name: 'JavaScript', level: 'advanced', yearsOfExperience: 5 },
          { name: 'HTML/CSS', level: 'advanced', yearsOfExperience: 5 },
          { name: 'Node.js', level: 'intermediate', yearsOfExperience: 2 },
          { name: 'Git', level: 'advanced', yearsOfExperience: 5 }
        ],
        experience: [
          {
            title: 'Desenvolvedora Frontend Sênior',
            company: 'TechCorp',
            duration: '2 anos',
            description: 'Desenvolvimento de aplicações web complexas usando Angular e TypeScript'
          },
          {
            title: 'Desenvolvedora Frontend',
            company: 'StartupXYZ',
            duration: '3 anos',
            description: 'Criação de interfaces responsivas e otimização de performance'
          }
        ],
        education: [
          {
            degree: 'Bacharelado em Ciência da Computação',
            institution: 'Universidade de São Paulo',
            year: 2019
          }
        ],
        preferredWorkType: 'Híbrido',
        salaryExpectation: {
          min: 12000,
          max: 18000
        },
        availability: 'Disponível para entrevistas',
        languages: ['Português (Nativo)', 'Inglês (Avançado)'],
        certifications: ['AWS Cloud Practitioner', 'Angular Certified Developer']
      },
      matchPercentage: 92,
      status: 'new',
      createdAt: new Date()
    },
    {
      id: '2',
      candidateId: 'candidate-2',
      jobId: 'job-1',
      candidate: {
        firstName: 'Carlos',
        lastName: 'Santos',
        anonymousId: 'CS_002',
        professionalSummary: 'Engenheiro de Software Full Stack com expertise em arquitetura de microsserviços e liderança técnica. Experiência sólida em transformação digital.',
        skills: [
          { name: 'Java', level: 'advanced', yearsOfExperience: 6 },
          { name: 'Spring Boot', level: 'advanced', yearsOfExperience: 5 },
          { name: 'Docker', level: 'advanced', yearsOfExperience: 4 },
          { name: 'Kubernetes', level: 'intermediate', yearsOfExperience: 3 },
          { name: 'AWS', level: 'advanced', yearsOfExperience: 4 },
          { name: 'PostgreSQL', level: 'advanced', yearsOfExperience: 6 },
          { name: 'Angular', level: 'intermediate', yearsOfExperience: 3 }
        ],
        experience: [
          {
            title: 'Arquiteto de Software',
            company: 'FinTech Corp',
            duration: '3 anos',
            description: 'Liderança técnica em migração para arquitetura de microsserviços'
          },
          {
            title: 'Desenvolvedor Full Stack Sênior',
            company: 'E-commerce Solutions',
            duration: '3 anos',
            description: 'Desenvolvimento de plataforma de e-commerce de alta performance'
          }
        ],
        education: [
          {
            degree: 'Mestrado em Engenharia de Software',
            institution: 'UNICAMP',
            year: 2018
          }
        ],
        preferredWorkType: 'Remoto',
        salaryExpectation: {
          min: 18000,
          max: 25000
        },
        availability: 'Disponível em 30 dias',
        languages: ['Português (Nativo)', 'Inglês (Fluente)', 'Espanhol (Intermediário)'],
        certifications: ['AWS Solutions Architect', 'Java Oracle Certified']
      },
      matchPercentage: 88,
      status: 'new',
      createdAt: new Date()
    },
    {
      id: '3',
      candidateId: 'candidate-3',
      jobId: 'job-1',
      candidate: {
        firstName: 'Marina',
        lastName: 'Costa',
        anonymousId: 'MC_003',
        professionalSummary: 'Product Manager com background técnico e paixão por inovação. Especialista em metodologias ágeis e análise de dados para tomada de decisões.',
        skills: [
          { name: 'Product Management', level: 'advanced', yearsOfExperience: 4 },
          { name: 'Scrum/Agile', level: 'advanced', yearsOfExperience: 5 },
          { name: 'SQL', level: 'intermediate', yearsOfExperience: 3 },
          { name: 'Analytics', level: 'advanced', yearsOfExperience: 4 },
          { name: 'Figma', level: 'intermediate', yearsOfExperience: 2 },
          { name: 'Jira', level: 'advanced', yearsOfExperience: 4 },
          { name: 'Python', level: 'basic', yearsOfExperience: 1 }
        ],
        experience: [
          {
            title: 'Senior Product Manager',
            company: 'Digital Bank',
            duration: '2 anos',
            description: 'Gestão de produtos financeiros digitais com foco em experiência do usuário'
          },
          {
            title: 'Product Owner',
            company: 'E-learning Platform',
            duration: '2 anos',
            description: 'Desenvolvimento de features educacionais usando metodologias ágeis'
          }
        ],
        education: [
          {
            degree: 'MBA em Gestão de Produtos Digitais',
            institution: 'FGV',
            year: 2021
          }
        ],
        preferredWorkType: 'Híbrido',
        salaryExpectation: {
          min: 15000,
          max: 22000
        },
        availability: 'Disponível imediatamente',
        languages: ['Português (Nativo)', 'Inglês (Avançado)', 'Francês (Básico)'],
        certifications: ['Certified Scrum Product Owner', 'Google Analytics Certified']
      },
      matchPercentage: 75,
      status: 'new',
      createdAt: new Date()
    }
  ];
  
  currentMatch: CompanyMatch | null = null;
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
      this.currentMatch = null;
    }
  }
  
  // Touch Events
  onTouchStart(event: TouchEvent) {
    if (this.isAnimating) return;
    
    this.isDragging = true;
    this.startX = event.touches[0].clientX;
    this.startY = event.touches[0].clientY;
    this.cardElement = event.target as HTMLElement;
    
    while (this.cardElement && !this.cardElement.classList.contains('candidate-card')) {
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
  
  // Mouse Events
  onMouseDown(event: MouseEvent) {
    if (this.isAnimating) return;
    
    this.isDragging = true;
    this.startX = event.clientX;
    this.startY = event.clientY;
    this.cardElement = event.target as HTMLElement;
    
    while (this.cardElement && !this.cardElement.classList.contains('candidate-card')) {
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
    
    const rotation = this.currentX * 0.1;
    const opacity = Math.max(0.7, 1 - Math.abs(this.currentX) / 300);
    
    this.cardElement.style.transform = `translate(${this.currentX}px, ${this.currentY}px) rotate(${rotation}deg)`;
    this.cardElement.style.opacity = opacity.toString();
    
    this.updateActionIndicators();
  }
  
  private updateActionIndicators() {
    const threshold = 100;
    
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
      if (this.currentX > 0) {
        this.animateCard('right');
      } else {
        this.animateCard('left');
      }
    } else if (this.currentY < -threshold) {
      this.animateCard('up');
    } else {
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
  
  onReject() {
    if (this.isAnimating) return;
    this.animateCard('left');
  }
  
  onInterest() {
    if (this.isAnimating) return;
    this.animateCard('right');
  }
  
  onPriorityMatch() {
    if (this.isAnimating) return;
    this.animateCard('up');
  }
  
  private animateCard(direction: 'left' | 'right' | 'up') {
    if (!this.cardElement) {
      this.cardElement = document.querySelector('.candidate-card') as HTMLElement;
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
                  direction === 'up' ? 'marcou como prioridade' : 'demonstrou interesse no';
                  
    console.log(`Empresa ${action} candidato: ${this.currentMatch?.candidate.anonymousId}`);
    
    setTimeout(() => {
      this.currentCard++;
      this.loadCurrentMatch();
      this.isAnimating = false;
      this.currentX = 0;
      this.currentY = 0;
      this.cardElement = null;
    }, 600);
  }
  
  refreshCandidates() {
    this.currentCard = 0;
    this.loadCurrentMatch();
  }
  
  formatSalary(min: number, max: number): string {
    return `R$ ${min.toLocaleString()} - R$ ${max.toLocaleString()}`;
  }
  
  getSkillLevelColor(level: string): string {
    switch (level) {
      case 'advanced': return '#4CAF50';
      case 'intermediate': return '#FF9800';
      case 'basic': return '#2196F3';
      default: return '#757575';
    }
  }
  
  getSkillLevelText(level: string): string {
    switch (level) {
      case 'advanced': return 'Avançado';
      case 'intermediate': return 'Intermediário';
      case 'basic': return 'Básico';
      default: return level;
    }
  }
  
  getAvailabilityIcon(availability: string): string {
    if (availability.includes('imediatamente')) return 'flash_on';
    if (availability.includes('30 dias')) return 'schedule';
    if (availability.includes('entrevistas')) return 'event_available';
    return 'access_time';
  }
  
  getWorkTypeIcon(workType: string): string {
    switch (workType.toLowerCase()) {
      case 'remoto': return 'home';
      case 'híbrido': return 'apartment';
      case 'presencial': return 'business';
      default: return 'work';
    }
  }
}
