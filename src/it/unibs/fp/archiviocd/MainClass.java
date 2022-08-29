package it.unibs.fp.archiviocd;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class MainClass {
	
	private static final String TITOLO_MENU = "Cosa vuoi fare?";
	private static final String [] VOCI_MENU = {"Aggiungi CD.", "Rimuovi CD.", "Visualizza CD.", "Visualizza archivio CD.", "Seleziona brano casuale."};
	
	private static final int MIN_SECONDI = 0;
	private static final int MAX_SECONDI = 59;
	private static final int MIN_DURATA_MINUTI_BRANO = 1;

	public static void main(String[] args) {
		
		ArchivioCd archivio = new ArchivioCd();

		MyMenu menu = new MyMenu(TITOLO_MENU, VOCI_MENU);
		
		boolean finito = false;
		
		do {
			int scelta = menu.scegli();
			finito = eseguiSceltaUtente(scelta, archivio);
			
		}while(!finito);
		
		System.out.println(StringheUtili.MSG_USCITA_PROGRAMMA);
		
	}

	/**
	 * <p>
	 * Esegue diverse azioni in base alla scelta dell'utente:
	 * <ol start="0">
	 * <li>Uscita dal programma in seguito ad un messaggio di conferma;</li>
	 * <li>Aggiunta di un CD all'archivio;</li>
	 * <li>Eliminazione di un CD dall'archivio (operazione possibile qualora l'archivio abbia almeno un CD);</li>
	 * <li>Visualizzazione di un CD richiesto dall'utente;</li>
	 * <li>Visualizzazione completa di tutto l'archivio CD;</li>
	 * <li>Selezione casuale di un brano.</li>
	 * </ol>
	 * </p>
	 * @param scelta
	 * @param archivio
	 * @return True se l'utente decide di terminare il programma, False altrimenti.
	 */
	public static boolean eseguiSceltaUtente(int scelta, ArchivioCd archivio) {
		switch(scelta) {
		case 0 : return InputDati.yesOrNo(StringheUtili.MSG_CONFERMA_USCITA);
		
				 
		case 1 : //Aggiunta di un CD.
				 aggiungiCD(archivio);
				 break;
				 
				  
		case 2 : //Eliminazione di un CD.
				 if(archivio.getElencoCD().size() != 0) 
				 {
			        eliminaCD(archivio);
				 }
				else
				{
					System.out.println(ArchivioCd.MSG_ARCHIVIO_VUOTO);
				}
				 break;
		
		case 3: //Visualizzazione di un CD specifico.
				if(archivio.getElencoCD().size() != 0)
				{
					visualizzaCD(archivio);
				}
				else
				{
					System.out.println(ArchivioCd.MSG_ARCHIVIO_VUOTO);
				}
				break;
				 
		case 4 : //Visualizzazione completa dell'archivio CD.
				 System.out.println();	
				 System.out.println(archivio.toString());
				 break;
				 
		case 5 : //Selezione casuale di un brano.
				 Brano brano = archivio.selezioneCasuale();
				 if(brano != null)
				 {
					System.out.println(brano.toString()); 
				 }
				 else
				 {
					 System.out.println(ArchivioCd.MSG_ARCHIVIO_VUOTO);
				 }
				break;
		}
		
		
		return false;
	}

	/**
	 * <p>
	 * Permette di aggiungere un CD nell'archivio qualora non sia gia' presente in quest'ultimo.
	 * Verra' inoltre chiesto all'utente di inserire almeno un brano all'interno del CD.
	 * </p>
	 * @param archivio
	 */
	public static void aggiungiCD(ArchivioCd archivio) {
		String titoloCD = InputDati.leggiStringaNonVuota(StringheUtili.MSG_TITOLO_CD);
		 
		if(archivio.trovaCD(titoloCD) != null) //Se il CD e' gia' presente nell'archivio l'utente viene informato.
		{
			System.out.println(String.format(StringheUtili.MSG_DUPLICATO_CD, titoloCD)); 
		}
		else
		{
			String autoreCD = InputDati.leggiStringaNonVuota(StringheUtili.MSG_AUTORE_CD);
			
			titoloCD.trim(); //Vengono rimossi gli spazi all'inizio e alla fine per evitare duplicati nascosti.
			
			Cd cdDaAggiungere = new Cd(titoloCD, autoreCD);
			archivio.aggiungiCD(cdDaAggiungere);
			System.out.println(StringheUtili.MSG_CD_AGGIUNTO);
			
			System.out.println();
			System.out.println(StringheUtili.MSG_ALMENO_UN_BRANO); //Viene rischiesto di inserire almeno un brano del CD.
			do {
			String titoloBrano = InputDati.leggiStringaNonVuota(StringheUtili.MSG_TITOLO_BRANO);
			
			if(cdDaAggiungere.trovaBrano(titoloBrano) != null) //Se il brano e' gia' presente nel CD l'utente viene informato.
			{
				System.out.println(String.format(StringheUtili.MSG_DUPLICATO_BRANO, titoloBrano));
			}
			else
			{
				int minutiBrano = InputDati.leggiInteroConMinimo(StringheUtili.MSG_MINUTI_BRANO, MIN_DURATA_MINUTI_BRANO);
				int secondiBrano = InputDati.leggiIntero(StringheUtili.MSG_SECONDI_BRANO, MIN_SECONDI, MAX_SECONDI);
				
				titoloBrano.trim(); //Vengono rimossi gli spazi all'inizio e alla fine per evitare duplicati nascosti.
				
				Brano branoDaAggiungere = new Brano(titoloBrano, minutiBrano, secondiBrano);
				cdDaAggiungere.aggiungiBrano(branoDaAggiungere);
				System.out.println(StringheUtili.MSG_BRANO_AGGIUNTO);
			}
			}while(InputDati.yesOrNo(StringheUtili.MSG_ALTRO_BRANO)); //Viene chiesto all'utente se vuole aggiungere un altro brano o terminare l'inserimento. 		 
		}	
	}
	
	/**
	 * <p>Permette di eliminare un CD selezionato dall'utente qualora sia presente nell'archivio.</p>
	 * @param archivio
	 */
	public static void eliminaCD(ArchivioCd archivio) {
		String titolo = InputDati.leggiStringaNonVuota(StringheUtili.MSG_CD_DA_ELIMINARE);
		
		Cd cd = archivio.trovaCD(titolo);
		
		if(cd != null)
		{
			boolean conferma = InputDati.yesOrNo(String.format(StringheUtili.MSG_CONFERMA_ELIMINAZIONE, titolo));
			if(conferma)
			{
				archivio.eliminaCD(cd);
				System.out.println(StringheUtili.MSG_CD_ELIMINATO);
			}
		}
		else
		{
			System.out.println(String.format(StringheUtili.MSG_CD_NON_PRESENTE, titolo));
		}
		
	}
	
	/**
	 * <p>Permette di visualizzare un CD cercato dall'utente qualora sia presente nell'archivio.</p>
	 * @param archivio
	 */
	public static void visualizzaCD(ArchivioCd archivio) {
		String titolo = InputDati.leggiStringaNonVuota(StringheUtili.MSG_CD_DA_VISUALIZZARE);
		Cd cd = archivio.trovaCD(titolo);
		
		if(cd != null)
		{
			System.out.println(cd.toString());
		}
		else
		{
			System.out.println(String.format(StringheUtili.MSG_CD_NON_PRESENTE, titolo));
		}
		
	}
	
	

}
