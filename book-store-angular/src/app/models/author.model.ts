export interface Author {
    id: string;
    full_name: string;
    pen_name: string;
    birthday: Date;
    deathDate: Date | null;
    nationality: string;
}