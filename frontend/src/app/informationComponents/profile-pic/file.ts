/**
 * Created by Malte Scheller on 16.11.16.
 */

export class File
{
    private _name: string;
    private _in: string;
    private _discription: string;


    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get in(): string {
        return this._in;
    }

    set in(value: string) {
        this._in = value;
    }

    get discription(): string {
        return this._discription;
    }

    set discription(value: string) {
        this._discription = value;
    }
}