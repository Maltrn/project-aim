/**
 * Created by Malte Scheller on 17.11.16.
 */
export class FileID
{
    private _item: string;

    get item(): string
    {
        return this._item;
    }

    set item(value: string)
    {
        this._item = value;
    }
}