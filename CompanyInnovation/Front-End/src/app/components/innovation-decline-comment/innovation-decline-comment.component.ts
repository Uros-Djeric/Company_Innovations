import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Innovation} from "../../model/innovation";
import {InnovationService} from "../../services/innovation/innovation.service";

@Component({
  selector: 'app-innovation-decline-comment',
  templateUrl: './innovation-decline-comment.component.html',
  styleUrls: ['./innovation-decline-comment.component.css']
})
export class InnovationDeclineCommentComponent implements OnInit{

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private innovationService: InnovationService,
  ) { }

  innovation: Innovation = new Innovation()

  formGroup: FormGroup = new FormGroup({
    reasonForDeclining : new FormControl(''),
  });

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      reasonForDeclining: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(30), Validators.pattern('[-_a-zA-Z]*')]],
    });

    //get an innovation from state
    this.innovation = history.state.innovation;
    console.log(this.innovation)
  }

  onSubmit(innovation: Innovation) {
    innovation.comment = this.formGroup.get('reasonForDeclining')?.value
    this.innovationService.approveOrDecline(innovation).subscribe(
      {
        next : (innovation: Innovation) => {
          console.log(JSON.stringify(innovation))
        }
      }
    )
    console.log(JSON.stringify(innovation))
    this.router.navigate(['innovation-list']).then()
  }

}
