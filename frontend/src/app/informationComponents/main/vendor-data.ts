/**
 * Created by Malte Scheller on 09.12.16.
 */

import { InMemoryDbService } from 'angular-in-memory-web-api';
export class VendorData implements InMemoryDbService {
    createDb() {
        let vendorInfo =
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
        return {vendorInfo};
    }
}

