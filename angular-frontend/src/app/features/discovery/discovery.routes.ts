import { Routes } from '@angular/router';

export const discoveryRoutes: Routes = [
  {
    path: '',
    redirectTo: 'candidate',
    pathMatch: 'full'
  },
  {
    path: 'candidate',
    loadComponent: () => import('./components/candidate-discovery/candidate-discovery.component').then(m => m.CandidateDiscoveryComponent)
  },
  {
    path: 'company',
    loadComponent: () => import('./components/company-discovery/company-discovery.component').then(m => m.CompanyDiscoveryComponent)
  },
  // Legacy route for backward compatibility
  {
    path: 'general',
    loadComponent: () => import('./components/discovery/discovery.component').then(m => m.DiscoveryComponent)
  }
];
