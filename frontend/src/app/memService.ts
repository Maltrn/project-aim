import {InMemoryDbService} from "angular2-in-memory-web-api";

export class MemService implements InMemoryDbService {
  createDb() {
    // let auth =
    // {
    //   "loginResponse": {
    //     "currentToken": "handsomeTOKEN",
    //     "username": "Maltron"
    //   },
    //   "produktInfoIds": ["Prod1", "Prod2", "Prod3", "Prod55"],
    //   "vendorInfoId": "IDVendorNr1"
    // };
    let vendor =
    {
      "facts": [
        {}
      ],
      "fileGallery": [
        "string"
      ],
      "iUploadCenter": {},
      "id": "IDVendorNr1",
      "longDescription": "This is a long description",
      "mainPic": "string",
      "name": "AllUNeed UnicornStore",
      "shortDescription": "This is a short description, cat with pants"
    };
    return {vendor};
  }
}

