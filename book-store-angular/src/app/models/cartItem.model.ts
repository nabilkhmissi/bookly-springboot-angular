import { Book } from "./book.model";

export interface CartItem {
    item: Book;
    quantity: number;
}