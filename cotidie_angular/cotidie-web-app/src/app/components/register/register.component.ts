import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { Router } from '@angular/router';
import { LoginDTO } from 'src/app/models/login';
import { AuthService } from 'src/app/services/auth.service';
import { HostListener } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  loginForm!: FormGroup;
  dto!: LoginDTO;
  // Declare height and width variables
  scrHeight: any;
  scrWidth: any;

  @HostListener('window:resize', ['$event'])
  getScreenSize() {
    this.scrHeight = window.innerHeight;
    this.scrWidth = window.innerWidth;
    console.log(this.scrHeight, this.scrWidth);
  }

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getScreenSize();
    this.loginForm = this.formBuilder.group({
      nick: [null, [Validators.required]],
      password: [null, [Validators.required]],
    });
  }

  submit(): void {
    if (!this.loginForm.valid) {
      return;
    }
    console.log(this.loginForm.value);
  }

  doLogin(): void {
    this.dto = this.loginForm.value;
    this.authService.login(this.dto).subscribe((resp) => {
      this.router.navigate(['/home']);
    });
  }
}
