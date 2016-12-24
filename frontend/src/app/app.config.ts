import {Injectable} from "@angular/core";

/**
 * Contains all configurable variables that are used globally in this app.
 *
 * This is a singleton that is provided and injected globally.
 */
@Injectable()
export class Settings {

  private _shortDescriptionMaxLength: number = 30;

  private _longDescriptionsMaxLength: number = 30;

  private _featureTabelMaxEntries: number = 20;

  private _fileGalleryMaxEntries: number = 30;

  private _uploadCenterPictureFileTypes: string = "jpeg,gif,png,jpg";

  // in bytes
  private _uploadCenterMaxFileSize: number = 1024;

  private _uploadCenterPDFFileTypes: string = "pdf";

  /**
   * Do not forget the trailing slash!
   * @type {string}
   * @private
   */
  private _backendApiBaseUrl: string = "http://aim.gartsy.de/api/";

  get shortDescriptionMaxLength(): number {
    return this._shortDescriptionMaxLength;
  }

  get longDescriptionsMaxLength(): number {
    return this._longDescriptionsMaxLength;
  }

  get featureTabelMaxEntries(): number {
    return this._featureTabelMaxEntries;
  }

  get fileGalleryMaxEntries(): number {
    return this._fileGalleryMaxEntries;
  }

  get uploadCenterMaxFileSize(): number {
    return this._uploadCenterMaxFileSize;
  }

  get uploadCenterPictureFileTypes(): string {
    return this._uploadCenterPictureFileTypes;
  }

  get uploadCenterPDFFileTypes(): string {
    return this._uploadCenterPDFFileTypes;
  }

  get backendApiBaseUrl(): string {
    return this._backendApiBaseUrl;
  }
}
