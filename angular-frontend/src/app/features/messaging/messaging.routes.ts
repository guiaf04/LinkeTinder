import { Routes } from '@angular/router';

export const messagingRoutes: Routes = [
  {
    path: '',
    loadComponent: () => import('./components/messaging/messaging.component').then(m => m.MessagingComponent)
  }
];
