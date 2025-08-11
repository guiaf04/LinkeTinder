import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDividerModule } from '@angular/material/divider';
import { UserType, LoginCredentials } from '../../../../core/interfaces/user.interface';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatCheckboxModule,
    MatProgressSpinnerModule,
    MatDividerModule
  ],
  templateUrl: './login-new.component.html',
  styleUrl: './login-new.component.scss'
})
export class LoginComponent implements OnInit {
  selectedUserType: UserType | null = null;
  candidateForm!: FormGroup;
  companyForm!: FormGroup;
  hidePassword = true;
  isLoading = false;

  // Admin credentials for testing
  private readonly ADMIN_CREDENTIALS = {
    candidate: {
      email: 'admin@candidate.com',
      password: 'admin123'
    },
    company: {
      email: 'admin@company.com', 
      password: 'admin123'
    }
  };

  constructor(
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit() {
    this.initializeForms();
  }

  private initializeForms() {
    // Candidate login form - prefilled with admin credentials for testing
    this.candidateForm = this.fb.group({
      email: [this.ADMIN_CREDENTIALS.candidate.email, [Validators.required, Validators.email]],
      password: [this.ADMIN_CREDENTIALS.candidate.password, [Validators.required, Validators.minLength(6)]],
      rememberMe: [false]
    });

    // Company login form - prefilled with admin credentials for testing
    this.companyForm = this.fb.group({
      email: [this.ADMIN_CREDENTIALS.company.email, [Validators.required, Validators.email]],
      password: [this.ADMIN_CREDENTIALS.company.password, [Validators.required, Validators.minLength(6)]],
      rememberMe: [false]
    });
  }

  selectUserType(type: UserType) {
    this.selectedUserType = type;
  }

  goBack() {
    this.selectedUserType = null;
  }

  async onCandidateLogin() {
    if (this.candidateForm.valid) {
      this.isLoading = true;
      
      const credentials: LoginCredentials = {
        email: this.candidateForm.value.email,
        password: this.candidateForm.value.password,
        userType: 'candidate'
      };

      try {
        // Check admin credentials
        if (this.validateAdminCredentials(credentials)) {
          console.log('✅ Admin candidate login successful:', credentials.email);
          
          // Simulate API call
          await new Promise(resolve => setTimeout(resolve, 1000));
          
          // Store user session
          this.storeUserSession('candidate', credentials.email);
          
          // Redirect to candidate discovery interface
          this.router.navigate(['/discovery/candidate']);
        } else {
          console.error('❌ Invalid credentials for candidate');
          alert('Credenciais inválidas! Use:\nEmail: admin@candidate.com\nSenha: admin123');
        }
      } catch (error) {
        console.error('Login error:', error);
        alert('Erro no login. Tente novamente.');
      } finally {
        this.isLoading = false;
      }
    }
  }

  async onCompanyLogin() {
    if (this.companyForm.valid) {
      this.isLoading = true;
      
      const credentials: LoginCredentials = {
        email: this.companyForm.value.email,
        password: this.companyForm.value.password,
        userType: 'company'
      };

      try {
        // Check admin credentials
        if (this.validateAdminCredentials(credentials)) {
          console.log('✅ Admin company login successful:', credentials.email);
          
          // Simulate API call
          await new Promise(resolve => setTimeout(resolve, 1000));
          
          // Store user session
          this.storeUserSession('company', credentials.email);
          
          // Redirect to company discovery interface
          this.router.navigate(['/discovery/company']);
        } else {
          console.error('❌ Invalid credentials for company');
          alert('Credenciais inválidas! Use:\nEmail: admin@company.com\nSenha: admin123');
        }
      } catch (error) {
        console.error('Login error:', error);
        alert('Erro no login. Tente novamente.');
      } finally {
        this.isLoading = false;
      }
    }
  }

  // Validate admin credentials
  private validateAdminCredentials(credentials: LoginCredentials): boolean {
    const adminCreds = this.ADMIN_CREDENTIALS[credentials.userType];
    return credentials.email === adminCreds.email && credentials.password === adminCreds.password;
  }

  // Store user session in localStorage
  private storeUserSession(userType: UserType, email: string): void {
    const sessionData = {
      userType,
      email,
      loginTime: new Date().toISOString(),
      isAuthenticated: true
    };
    
    localStorage.setItem('linkeTinder_session', JSON.stringify(sessionData));
    console.log('Session stored:', sessionData);
  }

  // Social login methods
  async loginWithGoogle() {
    // TODO: Implement Google OAuth
    console.log('Google login for:', this.selectedUserType);
  }

  async loginWithLinkedIn() {
    // TODO: Implement LinkedIn OAuth
    console.log('LinkedIn login for:', this.selectedUserType);
  }
}
