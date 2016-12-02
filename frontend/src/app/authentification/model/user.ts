/**
 * Created by Malte Scheller on 28.11.16.
 */

import {LoginResponse} from "./loginResponse";

export class User
{
    private _loginResponse: LoginResponse;
    private _produktInfoIds: string[];
    private _vendorInfoId: string;


    get loginResponse(): LoginResponse
    {
        return this._loginResponse;
    }

    set loginResponse(value: LoginResponse)
    {
        this._loginResponse = value;
    }

    get produktInfoIds(): string[]
    {
        return this._produktInfoIds;
    }

    set produktInfoIds(value: string[])
    {
        this._produktInfoIds = value;
    }

    get vendorInfoId(): string
    {
        return this._vendorInfoId;
    }

    set vendorInfoId(value: string)
    {
        this._vendorInfoId = value;
    }
}