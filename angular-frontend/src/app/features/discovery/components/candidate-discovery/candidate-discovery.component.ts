import { Component, OnInit, ViewChild, ElementRef, HostListener } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatBadgeModule } from '@angular/material/badge';
import { CandidateMatch } from '../../../../core/interfaces/user.interface';

@Component({
  selector: 'app-candidate-discovery',
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
  templateUrl: './candidate-discovery.component.html',
  styleUrl: './candidate-discovery.component.scss'
})
export class CandidateDiscoveryComponent implements OnInit {
  @ViewChild('cardStack') cardStack!: ElementRef;
  
  currentCard = 0;
  matches: CandidateMatch[] = [
    {
      id: '1',
      jobId: 'job-1',
      companyId: 'company-1',
      job: {
        title: 'Desenvolvedor Angular Sênior',
        company: 'TechCorp Inovações',
        companyLogo: '/assets/logos/techcorp.png',
        location: 'São Paulo, SP',
        workType: 'Híbrido',
        description: 'Estamos procurando um desenvolvedor Angular experiente para liderar projetos inovadores em nossa equipe de frontend. Você trabalhará com tecnologias de ponta e terá a oportunidade de impactar milhões de usuários.',
        requirements: [
          'Angular 14+ e TypeScript',
          'RxJS e NgRx',
          'Testes unitários (Jest/Jasmine)',
          'Git e metodologias ágeis',
          'Inglês intermediário'
        ],
        responsibilities: [
          'Desenvolver e manter aplicações Angular',
          'Colaborar com equipes de UX/UI',
          'Mentorar desenvolvedores juniores',
          'Participar de code reviews',
          'Implementar melhores práticas'
        ],
        benefits: [
          'Vale refeição R$ 30/dia',
          'Plano de saúde e odontológico',
          'Gympass',
          'Home office flexível',
          'Auxílio educação',
          'Stock options'
        ],
        salaryRange: {
          min: 12000,
          max: 18000
        },
        culture: [
          'Inovação constante',
          'Colaboração',
          'Diversidade e inclusão',
          'Aprendizado contínuo'
        ],
        companySize: '201-1000',
        industry: 'Tecnologia',
        website: 'https://techcorp.com'
      },
      matchPercentage: 95,
      status: 'new',
      createdAt: new Date()
    },
    {
      id: '2',
      jobId: 'job-2',
      companyId: 'company-2',
      job: {
        title: 'Frontend Lead',
        company: 'StartupXYZ',
        companyLogo: '/assets/logos/startupxyz.png',
        location: 'Remoto',
        workType: 'Remoto',
        description: 'Junte-se à nossa startup em crescimento exponencial como Frontend Lead. Você será responsável por construir a arquitetura frontend do zero e liderar uma equipe de desenvolvedores talentosos.',
        requirements: [
          'React ou Angular avançado',
          'Liderança técnica',
          'Arquitetura de software',
          'Performance e otimização',
          'Inglês fluente'
        ],
        responsibilities: [
          'Definir arquitetura frontend',
          'Liderar equipe de 5+ devs',
          'Tomar decisões técnicas',
          'Garantir qualidade do código',
          'Mentoring e desenvolvimento'
        ],
        benefits: [
          'Salário competitivo',
          'Equity significativo',
          'Flexibilidade total',
          'Budget para equipamentos',
          'Conferências e cursos'
        ],
        salaryRange: {
          min: 15000,
          max: 25000
        },
        culture: [
          'Startup mindset',
          'Velocidade de execução',
          'Ownership',
          'Impacto direto'
        ],
        companySize: '11-50',
        industry: 'Fintech',
        website: 'https://startupxyz.com'
      },
      matchPercentage: 87,
      status: 'new',
      createdAt: new Date()
    },
    {
      id: '3',
      jobId: 'job-3',
      companyId: 'company-3',
      job: {
        title: 'Product Manager Sênior',
        company: 'MegaCorp',
        companyLogo: '/assets/logos/megacorp.png',
        location: 'Rio de Janeiro, RJ',
        workType: 'Híbrido',
        description: 'Oportunidade única para atuar como Product Manager em uma das maiores empresas do país. Você será responsável por produtos digitais que impactam milhões de brasileiros diariamente.',
        requirements: [
          'Experiência como PM',
          'Data-driven decisions',
          'Metodologias ágeis',
          'SQL e Analytics',
          'Visão de negócio'
        ],
        responsibilities: [
          'Definir roadmap de produtos',
          'Coordenar equipes multidisciplinares',
          'Análise de métricas',
          'Pesquisa de usuários',
          'Go-to-market strategy'
        ],
        benefits: [
          'Pacote premium de benefícios',
          'Bônus por performance',
          'Previdência privada',
          'Cartão corporativo',
          'Licença parental estendida'
        ],
        salaryRange: {
          min: 18000,
          max: 30000
        },
        culture: [
          'Excelência operacional',
          'Foco no cliente',
          'Sustentabilidade',
          'Liderança'
        ],
        companySize: '1000+',
        industry: 'Varejo',
        website: 'https://megacorp.com.br'
      },
      matchPercentage: 78,
      status: 'new',
      createdAt: new Date()
    }
  ];
  
  currentMatch: CandidateMatch | null = null;
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
  
  // Mouse Events
  onMouseDown(event: MouseEvent) {
    if (this.isAnimating) return;
    
    this.isDragging = true;
    this.startX = event.clientX;
    this.startY = event.clientY;
    this.cardElement = event.target as HTMLElement;
    
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
                  direction === 'up' ? 'super curtiu' : 'se interessou por';
                  
    console.log(`Candidato ${action} a vaga: ${this.currentMatch?.job.title} na ${this.currentMatch?.job.company}`);
    
    setTimeout(() => {
      this.currentCard++;
      this.loadCurrentMatch();
      this.isAnimating = false;
      this.currentX = 0;
      this.currentY = 0;
      this.cardElement = null;
    }, 600);
  }
  
  refreshMatches() {
    this.currentCard = 0;
    this.loadCurrentMatch();
  }
  
  formatSalary(min: number, max: number): string {
    return `R$ ${min.toLocaleString()} - R$ ${max.toLocaleString()}`;
  }
  
  getBenefitIcon(benefit: string): string {
    if (benefit.includes('refeição') || benefit.includes('alimentação')) return 'restaurant';
    if (benefit.includes('saúde') || benefit.includes('médico')) return 'local_hospital';
    if (benefit.includes('odonto')) return 'dentistry';
    if (benefit.includes('gym') || benefit.includes('academia')) return 'fitness_center';
    if (benefit.includes('home') || benefit.includes('remoto')) return 'home';
    if (benefit.includes('educação') || benefit.includes('curso')) return 'school';
    if (benefit.includes('stock') || benefit.includes('equity')) return 'trending_up';
    if (benefit.includes('transporte') || benefit.includes('vale')) return 'directions_bus';
    return 'card_giftcard';
  }
}
