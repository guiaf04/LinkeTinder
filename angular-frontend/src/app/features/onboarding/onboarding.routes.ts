import { Routes } from '@angular/router';

export const onboardingRoutes: Routes = [
  {
    path: '',
    loadComponent: () => import('./components/onboarding/onboarding.component').then(m => m.OnboardingComponent)
  }
];
