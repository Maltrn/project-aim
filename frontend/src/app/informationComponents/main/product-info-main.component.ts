import {Component, OnInit} from "@angular/core";
import { ProductService } from "../main/product.service";
import { InfoDTO } from "../main/model/infoDTO";


@Component
({
    selector: 'product-info-main',
    templateUrl: './view/product-info-main.component.html'
})

/*
TODO Wird momentan von Dustin bearbeitet.
TODO Anzeigekonflikt Vendor/Product bewältigen.
 */
export class ProductInfoMain implements OnInit{
    productName: string = 'Produktname';
    product: InfoDTO;

    constructor(
        private productService: ProductService
    ) { }

    ngOnInit(): void {
        this.productService.loadMock();
      // this.productService.loadProductInformation();
        this.productName = this.productService.getProductInformationDto().name; // Mock
        console.log("Product-Info-Mail aufruf"); // Zum debuggen
    }
}