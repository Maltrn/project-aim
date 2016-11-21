/**
 * Created by Malte Scheller on 18.11.16.
 */
import {Fact} from "./fact";

export class InfoDTO
{
    private _id: string;
    private _name: string;
    private _shortDescription: string;
    private _longDescription: string;
    private _mainPic: string;
    private _fileGallery: string[];
    private _facts: Fact[];


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

    set fileGallery(value: Array)
    {
        this._fileGallery = value;
    }

    get facts(): Fact[]
    {
        return this._facts;
    }

    set facts(value: Array)
    {
        this._facts = value;
    }
}