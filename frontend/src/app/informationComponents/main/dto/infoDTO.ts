import {Fact} from "../model/fact";

export class InfoDTO
{
    private _id: string;
    private _name: string;
    private _shortDescription: string;
    private _longDescription: string;
    private _mainPic: string;
    private _fileGallery: string[];
    private _facts: Fact[]; 

    mockData(): InfoDTO
    { 
        var mockedVendor = new InfoDTO();
        mockedVendor._id = "11111111";
        mockedVendor._name = "Mr. Mock Vendor";
        mockedVendor._shortDescription = "Short Description";
        mockedVendor._longDescription = "Long Description";
        mockedVendor._mainPic = "iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAIAAAAC64paAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA2ZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDowNTgwMTE3NDA3MjA2ODExQjlFNzk0M0NDQ0U5OTM0OSIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDo2RjQ1ODhEMTY5N0YxMUUwOEU3MkZGNzdBRkIzQTQ4QyIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDo2RjQ1ODhEMDY5N0YxMUUwOEU3MkZGNzdBRkIzQTQ4QyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ1M1IE1hY2ludG9zaCI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOkQ0RkQwMzlERDAyMDY4MTFCRDM1RUMwRjYxRTI4QzdFIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjA1ODAxMTc0MDcyMDY4MTFCOUU3OTQzQ0NDRTk5MzQ5Ii8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+6wBNJwAAAmFJREFUeNqElM9rE0EUx+fNzP5KmjRRi61BLYpQkYiKLagHvfWigl48iAePHjx77V/gpeDJYxUv3hTx4kmwCIL2IlGrNIhrza9ms9vNbnZmfJtsi5hkG4aBPN5n33vf992FCzeWqDlFiCJ7/ZRKcgAo3jJoctALhPJ0TAgpBKIxTAE4xwuoUUBMppORkJh78vj+IzNFzmjb9dcqfzw/oETtUbMXyayl3b15bvHSieJkhlH4Xm0+ePi60fYsjaTBUqpIiGtXyneuniWQBDMZjUKiUCqslM7ZfLk0IN+srn+rNpqO73iBoTEcKA0OgggHDnuJKI+evUc+nzOmD0zoGpdRKrxv0jIMbupJTulgfuH0YRy74weip/ptq9G7wfv6Yvny/OxsqTgI3ru14HXDn7bz+PkHu+bobMzMuFQgcGZuBs9ucO4YeokcLRVXXnxEIcfCtO84u9apt7bzEzpOiMGW4+Pmvm7UO17AGQeIOCbBEMwYikmevvz06m3l/u2L508dwr/LT1a/bNRRRXc7MLCuHCMYQHx+bbardstxu4PgerW59vm3ZXGNM+ivmv9n+n9eA6xPKR6adMY50zSGDo2TARKTQN/rw7JDPwN2xtIY6HrMgyKRVHIA57LGzFQOzTi0MKxA0NvJnqfzrh+ieEpJu+a6nW4MGzpFP0RiBIy1LTMZLZc1C3kr9pZS9S0fn8uxOUzyuz0xpnJjy99suNi664VB0MMGpRy4CNBhqu0GlR81NVJ0RZZX3pkmbhXqTS+MxM6rGqvGI88mSoaxgCO/PcRzdq2AU4BKnkqE3/grwABhch2IY+MpzgAAAABJRU5ErkJggg";
        mockedVendor._fileGallery = ["File1", "File2", "File3"]; 
        var fact = new Fact();
        fact.name = "Fakt 1";
        fact.description = "Beschreibung zu Fakt 1";
        var fact2 = new Fact();
        fact2.name = "Fakt 2";
        fact2.description = "Beschreibung zu Fakt 2";
        mockedVendor._facts = [ fact, fact2];
        return mockedVendor;
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
