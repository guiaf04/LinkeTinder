import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, NavigationEnd, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDividerModule } from '@angular/material/divider';
import { filter, takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { TimeAgoPipe } from './shared/pipes/time-ago.pipe';

interface User {
  id: string;
  name: string;
  email: string;
  avatar?: string;
  type: 'candidate' | 'company';
}

interface Notification {
  id: string;
  title: string;
  message: string;
  icon: string;
  type: 'match' | 'message' | 'interview' | 'system';
  read: boolean;
  createdAt: Date;
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    MatProgressSpinnerModule,
    MatDividerModule,
    TimeAgoPipe
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'LinkeTinder';
  
  // State properties
  isAuthenticated = false;
  isLoading = false;
  loadingMessage = '';
  currentUser: User | null = null;
  
  // Navigation state
  currentRoute = '';
  hiddenHeaderRoutes = ['/auth/login', '/auth/register', '/onboarding'];
  hiddenBottomNavRoutes = ['/auth/login', '/auth/register', '/onboarding'];
  
  // Notifications
  notifications: Notification[] = [
    {
      id: '1',
      title: 'Novo Match!',
      message: 'Você tem um novo match com uma vaga de Desenvolvedor Angular',
      icon: 'favorite',
      type: 'match',
      read: false,
      createdAt: new Date(Date.now() - 1000 * 60 * 5) // 5 minutes ago
    },
    {
      id: '2',
      title: 'Nova Mensagem',
      message: 'TechCorp enviou uma mensagem para você',
      icon: 'message',
      type: 'message',
      read: false,
      createdAt: new Date(Date.now() - 1000 * 60 * 30) // 30 minutes ago
    },
    {
      id: '3',
      title: 'Entrevista Agendada',
      message: 'Sua entrevista foi confirmada para amanhã às 14h',
      icon: 'event',
      type: 'interview',
      read: true,
      createdAt: new Date(Date.now() - 1000 * 60 * 60 * 2) // 2 hours ago
    }
  ];
  
  private destroy$ = new Subject<void>();
  
  constructor(private router: Router) {
    // Simulate user authentication for demo
    this.simulateAuthentication();
  }
  
  ngOnInit() {
    // Listen to route changes
    this.router.events
      .pipe(
        filter(event => event instanceof NavigationEnd),
        takeUntil(this.destroy$)
      )
      .subscribe((event: NavigationEnd) => {
        this.currentRoute = event.url;
      });
  }
  
  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
  
  // Navigation helpers
  shouldShowHeader(): boolean {
    return !this.hiddenHeaderRoutes.includes(this.currentRoute);
  }
  
  shouldShowBottomNav(): boolean {
    return this.isAuthenticated && 
           !this.hiddenBottomNavRoutes.includes(this.currentRoute) &&
           this.isMobileDevice();
  }
  
  private isMobileDevice(): boolean {
    return window.innerWidth <= 768;
  }
  
  // Notification getters
  get notificationCount(): number {
    return this.notifications.filter(n => !n.read).length;
  }
  
  get unreadMessages(): number {
    return this.notifications
      .filter(n => n.type === 'message' && !n.read).length;
  }
  
  // User actions
  logout() {
    this.isLoading = true;
    this.loadingMessage = 'Fazendo logout...';
    
    // Simulate API call
    setTimeout(() => {
      this.isAuthenticated = false;
      this.currentUser = null;
      this.isLoading = false;
      this.loadingMessage = '';
      this.router.navigate(['/auth/login']);
    }, 1500);
  }
  
  // Notification actions
  markAllAsRead() {
    this.notifications.forEach(notification => {
      notification.read = true;
    });
  }
  
  handleNotificationClick(notification: Notification) {
    // Mark as read
    notification.read = true;
    
    // Navigate based on notification type
    switch (notification.type) {
      case 'match':
        this.router.navigate(['/discovery']);
        break;
      case 'message':
        this.router.navigate(['/messaging']);
        break;
      case 'interview':
        this.router.navigate(['/dashboard']);
        break;
      default:
        break;
    }
  }
  
  // Demo simulation
  private simulateAuthentication() {
    // Simulate loading and authentication
    this.isLoading = true;
    this.loadingMessage = 'Verificando autenticação...';
    
    setTimeout(() => {
      this.isAuthenticated = true;
      this.currentUser = {
        id: '1',
        name: 'João Silva',
        email: 'joao@email.com',
        avatar: '/assets/avatars/user1.jpg',
        type: 'candidate'
      };
      this.isLoading = false;
      this.loadingMessage = '';
    }, 2000);
  }
}
