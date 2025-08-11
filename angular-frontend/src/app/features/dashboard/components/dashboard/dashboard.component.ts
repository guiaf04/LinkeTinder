import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="dashboard-container">
      <h1>Dashboard - Em Desenvolvimento</h1>
      <p>Dashboard com analytics e métricas será implementado aqui.</p>
    </div>
  `,
  styles: [`
    .dashboard-container {
      padding: 2rem;
      text-align: center;
    }
  `]
})
export class DashboardComponent {
}
