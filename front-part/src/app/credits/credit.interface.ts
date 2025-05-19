export interface Credit {
    id?: number;
    amount: number;
    description: string;
    date: string;
    type: string;
    status: string;
    userId?: number;
    // Add any other fields that your backend returns
} 