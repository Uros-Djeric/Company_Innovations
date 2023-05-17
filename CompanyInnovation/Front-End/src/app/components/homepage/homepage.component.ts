import {Component, OnInit} from '@angular/core';
import {InnovationService} from "../../services/innovation/innovation.service";
import {Innovation} from "../../model/innovation";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit{

  innovations: Innovation[] = [];

  constructor(
    private innovationService: InnovationService
  ) {
  }

  ngOnInit() {
    this.innovationService.getInnovations()
      .subscribe(
      {
        next: (innovationsResponse: Innovation[]) => {
            this.innovations = innovationsResponse
        },
        error: (error: Error) => {
          console.log(error)
        }
      }
    )
  }


}
