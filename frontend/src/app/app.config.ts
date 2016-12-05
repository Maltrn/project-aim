import {Injectable} from "@angular/core";

@Injectable()
export class Configs {
    public static SHORT_DESCRIPTION_MAX_LENGTH: number = 30;
    public static LONG_DESCRIPTION_MAX_LENGTH: number = 30;
    public static FEATURETABLE_MAX_ENTRIES: number = 20;
    public static FILEGALLERY_MAX_ENTRIES: number = 30;
    public static UPLOADCENTER_PICTURES_FILETYPES: string = "jpeg,gif,png,jpg";
    public static UPLOADCENTER_PDF_FILETYPES: string = "pdf";
}