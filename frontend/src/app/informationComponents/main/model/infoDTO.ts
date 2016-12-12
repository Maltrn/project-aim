import {Fact} from "../model/fact";
import {IUploadCenter} from "./iUploadCenter";

export class InfoDTO
{
    private _facts: Fact[];
    private _fileGallery: string[];
    private _iUploadCenter: IUploadCenter;
    private _id: string;
    private _longDescription: string;
    private _mainPic: string;
    private _name: string;
    private _shortDescription: string;


    get iUploadCenter(): IUploadCenter
    {
        return this._iUploadCenter;
    }

    set iUploadCenter(value: IUploadCenter)
    {
        this._iUploadCenter = value;
    }

    get id(): string
    {
        return this._id;
    }

    set id(value: string)
    {
        this._id = value;
    }

    get name(): string
    {
        return this._name;
    }

    set name(value: string)
    {
        this._name = value;
    }

    get shortDescription(): string
    {
        return this._shortDescription;
    }

    set shortDescription(value: string)
    {
        this._shortDescription = value;
    }

    get longDescription(): string
    {
        return this._longDescription;
    }

    set longDescription(value: string)
    {
        this._longDescription = value;
    }

    get mainPic(): string
    {
        return this._mainPic;
    }

    set mainPic(value: string)
    {
        this._mainPic = value;
    }

    get fileGallery(): string[]
    {
        return this._fileGallery;
    }

    set fileGallery(value: string[])
    {
        this._fileGallery = value;
    }

    get facts(): Fact[]
    {
        return this._facts;
    }

    set facts(value: Fact[])
    {
        this._facts = value;
    }

}
