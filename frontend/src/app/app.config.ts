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

  private _featureTableMaxEntries: number = 20;

  private _fileGalleryMaxEntries: number = 30;

  private _uploadCenterImageFileTypes: string[] = ["image/jpeg","image/gif","image/png","image/jpg"];

  // in bytes
  private _uploadCenterMaxFileSize: number = 3145728;

  private _uploadCenterApplicationFileTypes: string[] = ["application/pdf"];

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

  get featureTableMaxEntries(): number {
    return this._featureTableMaxEntries;
  }

  get fileGalleryMaxEntries(): number {
    return this._fileGalleryMaxEntries;
  }

  get uploadCenterMaxFileSize(): number {
    return this._uploadCenterMaxFileSize;
  }

  get uploadCenterImageFileTypes(): string[] {
    return this._uploadCenterImageFileTypes;
  }

  get uploadCenterApplicationFileTypes(): string[] {
    return this._uploadCenterApplicationFileTypes;
  }

  get backendApiBaseUrl(): string {
    return this._backendApiBaseUrl;
  }
}
