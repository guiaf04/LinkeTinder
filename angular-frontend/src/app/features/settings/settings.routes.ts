import { Routes } from '@angular/router';

export const settingsRoutes: Routes = [
  {
    path: '',
    loadComponent: () => import('./components/settings/settings.component').then(m => m.SettingsComponent)
  }
];
