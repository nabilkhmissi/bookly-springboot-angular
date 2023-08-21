import { Book } from "./book.model";

export interface Wishlist {
    id: string;
    items: Book[]
}