export interface Credit {
    id: number;
    typeCredit: string;
    dateAcception: string | null;
    dateDemande: string;
    dureeRemboursement: number;
    montant: number;
    statut: 'ACCEPTE' | 'EN_ATTENTE' | 'REFUSE';
    tauxInteret: number;
    typeBien: string | null;
    motif: string | null;
    raisonSociale: string | null;
    clientId: number;
    remboursements: any[] | null;
} 