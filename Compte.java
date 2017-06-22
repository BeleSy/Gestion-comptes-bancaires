package ca.qc.collegeahuntsic;

public class Compte {
	private String numero; //variables d'instance de la classe
	private String type;
	private double solde;
	private String devise;
	
	//méthode constructeur qui crée l'objet
	public Compte(String numero, String type, double solde, String devise){ //variables locales du constructeur
		this.numero = numero; //initiation var d'instances
		this.type = type;
		this.solde = solde;
		this.devise = devise;
	}
	
	public String toString(){ //pour pouvoir effectuer des tests et afficher
		String chaine = numero + ";" + type + ";" + solde + ";" + devise;
		return chaine;
	}
	
	public void crediter(double montant){
		solde = solde + montant;
	}
	
	public void debiter(double montant){
		if (solde >= montant){
			solde = solde - montant;
		}
		else{
			System.out.println("Insuffisance de fond - Le solde du compte est " + solde + " " + devise);
		}
	}
	public String getNumero(){
		return numero;
	}
	public double getSolde(){
		return solde;
	}
}