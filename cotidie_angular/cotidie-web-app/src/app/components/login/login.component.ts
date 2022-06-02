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


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  dto!: LoginDTO;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
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
