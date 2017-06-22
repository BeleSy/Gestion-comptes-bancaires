package ca.qc.collegeahuntsic;

import javax.swing.JOptionPane;
public class Principale {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//déclaration variables
		int choixMenu;
		GestionCompte gestionCompte = new GestionCompte();
		gestionCompte.chargerFichier(".\\resources\\listeComptes.dat");
		
		do{
			String saisie = JOptionPane.showInputDialog("Choisissez l'une des options suivantes:"
			+ "\n1) Ouvrir un compte"
			+ "\n2) Débiter un compte"
			+ "\n3) Crédtier un compte"
			+ "\n4) Lister les comptes"
			+ "\n5) Rechercher un compte"
			+ "\n6) Trier les comptes en ordre croissant des soldes"
			+ "\n7) Sortir du programme");
			choixMenu = Integer.parseInt(saisie);
			
			switch (choixMenu){ 
			case 1: ouvrirCompte(gestionCompte);
					break;
			case 2: debiterCompte(gestionCompte);
					break;
			case 3: crediterCompte(gestionCompte);
					break; 
			case 4: gestionCompte.afficherComptes();
					break; 
			case 5: rechercherCompte(gestionCompte);
					break;
			case 6: gestionCompte.afficherComptesOrdreCroissant();
					break;
			case 7: gestionCompte.enregistrerFichier(".\\resources\\listeComptes.dat");
					break;
			default : JOptionPane.showMessageDialog(null, "Choix invalide");
			}
		}while (choixMenu != 7);
	}
	
	public static void ouvrirCompte(GestionCompte gestionCompte){
		String numero = JOptionPane.showInputDialog("Entrer le numero de compte");
		String type = JOptionPane.showInputDialog("Entrer le type de compte");
		double solde = Double.parseDouble(JOptionPane.showInputDialog("Entrer le solde du compte"));
		String devise = JOptionPane.showInputDialog("Entrer la devise du compte");
		Compte c = new Compte(numero, type, solde, devise);
		gestionCompte.ajouterCompte(c);
	}
	
	public static void debiterCompte(GestionCompte gestionCompte){
		String numero = JOptionPane.showInputDialog("Entrer le numero de compte à débiter");
		Compte compteRecherche = gestionCompte.rechercherCompte(numero);
		if (compteRecherche == null){
			 JOptionPane.showMessageDialog(null, "Ce compte n'existe pas");
		}
		double montant = Double.parseDouble(JOptionPane.showInputDialog("Entrer le montant du retrait"));
		compteRecherche.debiter(montant);
	}
	
	public static void crediterCompte(GestionCompte gestionCompte){
		String numero = JOptionPane.showInputDialog("Entrer le numero de compte à créditer");
		Compte compteRecherche = gestionCompte.rechercherCompte(numero);
		if (compteRecherche == null){
			 JOptionPane.showMessageDialog(null, "Ce compte n'existe pas");
		}
		double montant = Double.parseDouble(JOptionPane.showInputDialog("Entrer le montant du dépôt"));
		compteRecherche.crediter(montant);
	}
	public static void rechercherCompte(GestionCompte gestionCompte){
		String numero = JOptionPane.showInputDialog("Entrer le numero de compte à rechercher");
		Compte compteRecherche = gestionCompte.rechercherCompte(numero);
		if (compteRecherche == null){
			 JOptionPane.showMessageDialog(null, "Ce compte n'existe pas");
		}
		else{
			JOptionPane.showMessageDialog(null, compteRecherche);
		}
	}

}
