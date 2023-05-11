import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { CognitoService } from 'src/app/services/cognito/cognito.service';
import { StorageService } from 'src/app/services/storage/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private cognitoService: CognitoService,
    private storageService: StorageService
  ) { }

  formGroup: FormGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(30), Validators.pattern('[-_a-zA-Z0-9]*')]],
      password: ['', [Validators.required, Validators.minLength(3), Validators.pattern('[-0-9]*')]]
    });
  }

  onSubmit() {
    this.cognitoService.signIn(this.formGroup.get('username')?.value,this.formGroup.get('password')?.value).then((value) => {
      console.log(value.signInUserSession.accessToken.jwtToken)
      var jwt = value.signInUserSession.accessToken.jwtToken
      this.storageService.storeTokenData(jwt);
      this.router.navigateByUrl("")
})
.catch((error:any) => {
  alert("Error.")
})
  }

}
