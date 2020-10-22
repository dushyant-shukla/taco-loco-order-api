import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BackendService } from './backend-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'taco-loco';
  menu = <any>[];
  selectedItems = <any>[];
  order = <any>[];
  totalPrice = 0;
  discountedPrice = 0;
  errorMsg = "";



  constructor(private backend: BackendService) { }

  ngOnInit() {
    this.getMenu();
  }

  getMenu() {
    this.backend.getMenu()
      .subscribe(menu => {
        this.menu = menu.items.map(obj => ({ ...obj, quantity: 0 }))
      });
  }

  selectItem(item, event) {
    if (event.target.checked) {
      this.selectedItems.push(item);
    } else {
      var removeIndex = this.selectedItems.map(function (obj) { return obj.id; }).indexOf(item.id);
      this.selectedItems.splice(removeIndex, 1);
    }

    console.log(this.selectedItems);
  }

  placeOrder() {
    let validOrder = true;
    this.selectedItems.forEach(element => {
      if (!element.quantity) {
        this.errorMsg = "You cannot place items with 0 quantity!!!";
        this.order = [];
        validOrder = false;
        return;
      }
    });

    if (this.selectedItems.length && validOrder) {
      this.errorMsg = "";
      this.backend.placeOrder({ items: this.selectedItems })
        .subscribe(order => {
          this.order = order.items;
          this.totalPrice = order['total-price'];
          this.discountedPrice = order['discounted-price'];
        });
    }
  }

  trackByFn(index: any, item: any) {
    return index;
  }
}
