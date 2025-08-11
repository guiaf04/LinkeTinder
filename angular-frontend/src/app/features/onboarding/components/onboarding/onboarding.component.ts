import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-onboarding',
  standalone: true,
  imports: [CommonModule],
  template: `<div class="onboarding-container"><h1>Onboarding - Em Desenvolvimento</h1></div>`,
  styles: [`
    .onboarding-container {
      padding: 2rem;
      text-align: center;
    }
  `]
})
export class OnboardingComponent {
}
