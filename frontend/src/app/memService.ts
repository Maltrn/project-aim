/**
 * Created by Malte Scheller on 09.12.16.
 */

import {InMemoryDbService} from 'angular-in-memory-web-api';
export class MemService implements InMemoryDbService
{
    createDb()
    {
        let auth =
        {
            "loginResponse": {
                "currentToken": "handsomeTOKEN",
                "username": "Maltron"
            },
            "produktInfoIds": ["Prod1", "Prod2", "Prod3", "Prod55"],
            "vendorInfoId": "IDVendorNr1"
        };
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
        return {auth, vendor};
    }
}

