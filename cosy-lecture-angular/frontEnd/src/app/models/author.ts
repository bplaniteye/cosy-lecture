export class Author{
    public id!: number;
    public firstname!: string;
    public lastname!: string;
    public bio!: string;
    public birthday!: Date;
    public deathday: Date | null = null;
};
