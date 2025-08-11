import { Routes } from '@angular/router';

export const routes: Routes = [
  // Default redirect
  {
    path: '',
    redirectTo: '/auth/login',
    pathMatch: 'full'
  },
  
  // Authentication routes
  {
    path: 'auth',
    loadChildren: () => import('./features/auth/auth.routes').then(m => m.authRoutes)
  },
  
  // Discovery/Swipe interface
  {
    path: 'discovery',
    loadChildren: () => import('./features/discovery/discovery.routes').then(m => m.discoveryRoutes)
  },
  
  // Dashboard
  {
    path: 'dashboard',
    loadChildren: () => import('./features/dashboard/dashboard.routes').then(m => m.dashboardRoutes)
  },
  
  // Profile management
  {
    path: 'profile',
    loadChildren: () => import('./features/profile/profile.routes').then(m => m.profileRoutes)
  },
  
  // Messaging
  {
    path: 'messaging',
    loadChildren: () => import('./features/messaging/messaging.routes').then(m => m.messagingRoutes)
  },
  
  // Settings
  {
    path: 'settings',
    loadChildren: () => import('./features/settings/settings.routes').then(m => m.settingsRoutes)
  },
  
  // Onboarding
  {
    path: 'onboarding',
    loadChildren: () => import('./features/onboarding/onboarding.routes').then(m => m.onboardingRoutes)
  },
  
  // Fallback route
  {
    path: '**',
    redirectTo: '/discovery'
  }
];
