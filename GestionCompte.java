package ca.qc.collegeahuntsic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class GestionCompte {
	private Compte [] listeComptes; //tableau de type comptes 
	private int nombreCompte;
	
	public GestionCompte(){ //constructeur
		listeComptes = new Compte[20]; //'new' obligé quand c'est un objet
		nombreCompte = 0;
	}
	
	public void ajouterCompte(Compte indexCompte){
		if (nombreCompte == 20){
			JOptionPane.showInputDialog(null, "Impossible de rajouter d'autres comptes");
		}
		else{
			listeComptes[nombreCompte] = indexCompte;
			nombreCompte++;
		}		
	}
	
	public void afficherComptes(){
		String chaine = convertirTabChaine(listeComptes);
		JOptionPane.showMessageDialog(null, chaine);
	}
	
	
	public Compte rechercherCompte(String numero){ //-1 si null
		Compte objetTrouve = null;
		for (int i = 0; (i < nombreCompte)&& (objetTrouve == null); i++){
			if (numero.equals(listeComptes[i].getNumero()) == true){
				objetTrouve = listeComptes[i];
			}
		}
		return objetTrouve;
	}
	
	public void chargerFichier(String nomFichier){
		try{
			FileReader fr = new FileReader(nomFichier);
			BufferedReader bw = new BufferedReader(fr);
			String ligneLue;
			do{
				ligneLue = bw.readLine();
				if (ligneLue != null){
					StringTokenizer tokens = new StringTokenizer(ligneLue,";");
					String numero = tokens.nextToken();
					String type = tokens.nextToken();
					double solde = Double.parseDouble(tokens.nextToken());
					String devise = tokens.nextToken();
					Compte c = new Compte(numero, type, solde, devise);
					ajouterCompte(c);
				}
				
			}while(ligneLue != null);
			bw.close();
		}
		catch(FileNotFoundException ex){
			System.out.println(ex.getMessage());
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void enregistrerFichier(String nomFichier){
		try{
			FileWriter fw = new FileWriter(nomFichier);
			PrintWriter pw = new PrintWriter(fw);
			for (int i=0; i<nombreCompte; i++){
				if (i < nombreCompte -1){
					pw.println(listeComptes[i]);
				}
				else{
					pw.print(listeComptes[i]);
				}
			}
			pw.close();
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void afficherComptesOrdreCroissant(){
		Compte[] listeComptesTri = new Compte[nombreCompte];
		//copie listeComptes
		for (int i = 0; i < nombreCompte; i++){
			listeComptesTri[i] = listeComptes[i];
		}
		
		trier(listeComptesTri);
		String chaine = convertirTabChaine(listeComptesTri);
		JOptionPane.showMessageDialog(null, chaine);
	}
	
	public String convertirTabChaine(Compte[] tab){
		String compteChaine ="";
		for (int i = 0; i< nombreCompte; i++){
			compteChaine += tab[i] + "\n";
		}
		return compteChaine;
	}
	
	//méthodes de tri à bulles
	public void trier(Compte[] tab){
		boolean echangesEnCours;
		int iDernier = tab.length -1;
		Compte temp;
		do{
			echangesEnCours = false;
			for(int i = 0; i< iDernier; i++){
				if(tab[i].getSolde() > tab[i+1].getSolde()){
					temp = tab[i];
					tab[i] = tab[i +1];
					tab[i+1] = temp;
					echangesEnCours = true;
				}
			}
			iDernier--;
		}while(echangesEnCours);
	}
	
}

