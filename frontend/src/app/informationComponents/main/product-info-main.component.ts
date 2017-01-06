import {Component, OnInit} from "@angular/core";
import {InfoDTO} from "../main/model/infoDTO";
import {UserService} from "../../authentication/user.service";
import {ProductService} from "../../product/product.service";


@Component
({
  selector: 'product-info-mains',
  templateUrl: './view/product-info-main.component.html'
})

export class ProductInfoMain implements OnInit {

  private products;
  productName: string = 'Produktname';
  product: InfoDTO;

  constructor(private productService: ProductService, private userService: UserService) {
  }

  ngOnInit(): void {
    // this.productService.loadMock();
    // this.productService.loadProductInformation();
    // this.productName = this.productService.getProductInformationDto().name; // Mock
    console.log("Product-Info-Mail aufruf"); // Zum debuggen
  }

  private loadProducts() {
    this.productService.getAllProducts().subscribe(
      data => this.products = data,
      error => {
        console.log("ERROR in REST API");
        if (error.indexOf("401") !== -1) {
          this.userService.logout();
        }
      });
  }
}
