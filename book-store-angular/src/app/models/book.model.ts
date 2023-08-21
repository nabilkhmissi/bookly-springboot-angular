import { Author } from "./author.model";

export interface Book {
    id: string;
    title: string;
    description: string;
    pages: number;
    author: Author;
    edition: string;
    year: number;
    rating: number;
    tags: string[];
    addedDate: Date;
    quantity: number;
    price: number;
    category: string;
    image: string;
    sale_number: number;
}