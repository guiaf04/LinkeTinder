// User Types and Interfaces
export type UserType = 'candidate' | 'company';

export interface BaseUser {
  id: string;
  email: string;
  type: UserType;
  avatar?: string;
  createdAt: Date;
  isActive: boolean;
}

export interface Candidate extends BaseUser {
  type: 'candidate';
  profile: CandidateProfile;
  preferences: CandidatePreferences;
}

export interface Company extends BaseUser {
  type: 'company';
  profile: CompanyProfile;
  preferences: CompanyPreferences;
}

export interface CandidateProfile {
  // Public info (visible to companies)
  skills: string[];
  experience: string;
  summary: string;
  seniority: 'junior' | 'pleno' | 'senior' | 'especialista';
  
  // Private info (hidden from companies during matching)
  fullName?: string;
  phone?: string;
  location?: string;
  linkedInUrl?: string;
  githubUrl?: string;
  portfolioUrl?: string;
  cv?: string;
}

export interface CompanyProfile {
  companyName: string;
  businessName?: string;
  cnpj?: string;
  industry: string;
  companySize: '1-10' | '11-50' | '51-200' | '201-1000' | '1000+';
  description: string;
  website?: string;
  location: string;
  benefits: string[];
  culture: string[];
  logoUrl?: string;
}

export interface CandidatePreferences {
  desiredRoles: string[];
  salaryRange: {
    min: number;
    max: number;
  };
  workType: 'remote' | 'hybrid' | 'onsite' | 'any';
  locations: string[];
  companySize: string[];
  benefits: string[];
}

export interface CompanyPreferences {
  targetSeniority: string[];
  requiredSkills: string[];
  preferredSkills: string[];
  workType: 'remote' | 'hybrid' | 'onsite';
  maxSalaryBudget: number;
}

// Job interfaces for companies
export interface Job {
  id: string;
  companyId: string;
  title: string;
  description: string;
  requirements: string[];
  responsibilities: string[];
  benefits: string[];
  salaryRange: {
    min: number;
    max: number;
  };
  workType: 'remote' | 'hybrid' | 'onsite';
  location: string;
  seniority: 'junior' | 'pleno' | 'senior' | 'especialista';
  skills: string[];
  isActive: boolean;
  createdAt: Date;
  applications: JobApplication[];
}

export interface JobApplication {
  id: string;
  candidateId: string;
  jobId: string;
  status: 'pending' | 'liked' | 'disliked' | 'matched' | 'interview' | 'hired' | 'rejected';
  appliedAt: Date;
  matchPercentage: number;
}

// Match interfaces for different user types
export interface CandidateMatch {
  id: string;
  jobId: string;
  companyId: string;
  job: {
    title: string;
    company: string;
    companyLogo?: string;
    location: string;
    workType: string;
    description: string;
    requirements: string[];
    responsibilities: string[];
    benefits: string[];
    salaryRange: {
      min: number;
      max: number;
    };
    culture: string[];
    companySize: string;
    industry: string;
    website?: string;
  };
  matchPercentage: number;
  status: 'new' | 'liked' | 'disliked' | 'matched';
  createdAt: Date;
}

export interface Skill {
  name: string;
  level: 'basic' | 'intermediate' | 'advanced';
  yearsOfExperience: number;
}

export interface Experience {
  title: string;
  company: string;
  duration: string;
  description: string;
}

export interface Education {
  degree: string;
  institution: string;
  year: number;
}

export interface CompanyMatch {
  id: string;
  candidateId: string;
  jobId: string;
  candidate: {
    // Anonymous identifier for companies to track
    firstName: string;
    lastName: string;
    anonymousId: string;
    
    // Professional info (visible to companies)
    professionalSummary: string;
    skills: Skill[];
    experience: Experience[];
    education: Education[];
    
    // Preferences and availability
    preferredWorkType: string;
    salaryExpectation: {
      min: number;
      max: number;
    };
    availability: string;
    languages: string[];
    certifications: string[];
    
    // NO personal contact data: email, phone, address, etc.
  };
  matchPercentage: number;
  status: 'new' | 'liked' | 'disliked' | 'matched';
  createdAt: Date;
}

// Auth interfaces
export interface LoginCredentials {
  email: string;
  password: string;
  userType: UserType;
}

export interface RegisterData {
  email: string;
  password: string;
  confirmPassword: string;
  userType: UserType;
  agreeToTerms: boolean;
}

export interface AuthResponse {
  user: Candidate | Company;
  token: string;
  refreshToken: string;
}
