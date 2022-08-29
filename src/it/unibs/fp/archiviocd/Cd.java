package it.unibs.fp.archiviocd;

import java.util.ArrayList;
import java.util.List;

import it.unibs.fp.mylib.EstrazioniCasuali;

public class Cd {

	private static final String INDICIZZAZIONE_BRANI = "  %d. ";

	private static final String DESCRIZIONE_CD = "Titolo: %s, autore: %s, elenco brani:%n%s";

	private static final int MIN_ZERO = 0;
	
	private String titolo;
	private String autore;
	private List<Brano> elencoBrani;
	
	public Cd(String _titolo, String _autore) {
		this.titolo = _titolo;
		this.autore = _autore;
		this.elencoBrani = new ArrayList<Brano>();
	}
	
	/**
	 * <p>Aggiunge un brano al CD.</p>
	 * @param branoDaAggiungere
	 */
	public void aggiungiBrano(Brano branoDaAggiungere) {
		this.elencoBrani.add(branoDaAggiungere);
	}
	
	/**
	 * <p>Permette di trovare un brano nel CD tramite titolo inserito dall'utente.</p>
	 * @param titoloCercato
	 * @return Il brano cercato se presente nel CD, null altrimenti.
	 */
	public Brano trovaBrano(String titoloCercato) {
		for(Brano brano : this.elencoBrani) {
			if(brano.confrontaTitoloBrano(titoloCercato))
			{
				return brano;
			}
		}
		
		return null;
	}

	/**
	 * <p>Ritorna un brano scelto casualmente attraverso la classe di utilita' EstrazioniCasuali.</p>
	 * @return Brano scelto casualmente.
	 */
	public Brano selezioneCasualeBrano() {
		if(this.elencoBrani.size() == 0)
		{
			return null;
		}
		return this.elencoBrani.get(EstrazioniCasuali.estraiIntero(MIN_ZERO, this.elencoBrani.size() - 1));
	}
	
	/**
	 * <p>Confronta il titolo cercato dall'utente con quello del CD.</p>
	 * @param titoloCercato
	 * @return True se il titolo cercato corrisponde a quello del CD, False altrimenti.
	 */
	public boolean confrontaTitoloCD(String titoloCercato) {
		titoloCercato.trim();
		return this.titolo.equalsIgnoreCase(titoloCercato);
	}
	
	
	/**
	 * <p>
	 * Fornisce una descrizione del CD mostrando:
	 * <ul>
	 * <li>Autore del CD;</li>
	 * <li>Titolo del CD;</li>
	 * <li>Elenco dei brani al suo interno (con la loro descrizone).</li>
	 * </ul>
	 * </p>
	 * @return Descrizione tramite stringa dell'oggetto Cd.
	 */
	@Override
	public String toString() {
		StringBuffer descrizioneBrano = new StringBuffer();
		
		for(int i = 0; i < this.elencoBrani.size(); i++) {
			Brano brano = this.elencoBrani.get(i);
			descrizioneBrano.append(String.format(INDICIZZAZIONE_BRANI, i+1));
			descrizioneBrano.append(brano.toString());
		}
		
		StringBuffer descrizioneCD = new StringBuffer();
		
		descrizioneCD.append(String.format(DESCRIZIONE_CD, this.titolo, this.autore, descrizioneBrano));
		
		return descrizioneCD.toString();
	}
	
	public String getTitolo() {
		return titolo;
	}
	
}
