import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'timeAgo',
  standalone: true
})
export class TimeAgoPipe implements PipeTransform {
  transform(value: Date): string {
    if (!value) return '';
    
    const now = new Date().getTime();
    const time = new Date(value).getTime();
    const difference = now - time;
    
    const seconds = Math.floor(difference / 1000);
    const minutes = Math.floor(seconds / 60);
    const hours = Math.floor(minutes / 60);
    const days = Math.floor(hours / 24);
    const weeks = Math.floor(days / 7);
    const months = Math.floor(days / 30);
    const years = Math.floor(days / 365);
    
    if (seconds < 60) {
      return 'agora';
    } else if (minutes < 60) {
      return `${minutes}m atrás`;
    } else if (hours < 24) {
      return `${hours}h atrás`;
    } else if (days < 7) {
      return `${days}d atrás`;
    } else if (weeks < 4) {
      return `${weeks}sem atrás`;
    } else if (months < 12) {
      return `${months}mês atrás`;
    } else {
      return `${years}ano${years > 1 ? 's' : ''} atrás`;
    }
  }
}
