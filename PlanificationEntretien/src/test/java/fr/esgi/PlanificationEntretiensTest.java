package fr.esgi;

import fr.esgi.exceptions.*;
import fr.esgi.infraStructure.insideApi.DateService;
import fr.esgi.infraStructure.insideApi.DateServiceTestImpl;
import fr.esgi.infraStructure.outsideApi.*;
import fr.esgi.models.*;
import fr.esgi.useCases.PlanificationEntretiens;
import fr.esgi.useCases.impl.PlanificationEntretienBusiness;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.*;

public class PlanificationEntretiensTest {
    @Mock
    ApiClient apiClient;

    @Spy
    PersistanceService persistanceService = new PersistanceServiceTestImpl();

    @Spy
    DateService dateService = new DateServiceTestImpl();

    @InjectMocks
    PlanificationEntretiens planificationEntretiens = new PlanificationEntretienBusiness(apiClient,persistanceService,dateService);

    @Before
    public void init() {
        //Prise en compte des annotations Mockito
        MockitoAnnotations.initMocks(this);

        //Creation des consultants recruteur mockito
        List<ConsultantRecruteur> consultantRecruteurs = new ArrayList<>();

        //Consultant recruteur 1
        List<Compétence> compétences1 = new ArrayList<>();
        compétences1.add(Compétence.AGILITE);
        ConsultantRecruteur consultantRecruteur1 = ConsultantRecruteur.builder().Compétences(compétences1).Nom("Consultant1").hoursFree(new int[]{18,19}).build();
        consultantRecruteurs.add(consultantRecruteur1);

        //Consultant recruteur 2
        List<Compétence> compétences2 = new ArrayList<>();
        compétences2.add(Compétence.DOTNET);
        ConsultantRecruteur consultantRecruteur2 = ConsultantRecruteur.builder().Compétences(compétences2).Nom("Consultant2").hoursFree(new int[]{18,19}).build();
        consultantRecruteurs.add(consultantRecruteur2);

        //Consultant recruteur 3
        List<Compétence> compétences3 = new ArrayList<>();
        compétences3.add(Compétence.JS);
        ConsultantRecruteur consultantRecruteur3 = ConsultantRecruteur.builder().Compétences(compétences3).Nom("Consultant3").hoursFree(new int[]{18,19}).build();
        consultantRecruteurs.add(consultantRecruteur3);

        //Consultant recruteur 4
        List<Compétence> compétences4 = new ArrayList<>();
        compétences4.add(Compétence.JAVA);
        compétences4.add(Compétence.JS);
        ConsultantRecruteur consultantRecruteur4 = ConsultantRecruteur.builder().Compétences(compétences4).Nom("Consultant4").hoursFree(new int[]{18,19}).build();
        consultantRecruteurs.add(consultantRecruteur4);

        MoisCreneau moisCreneauRecherche = MoisCreneau.builder()
                .mois(3)
                .annee(2019).build();
        Mockito.when(apiClient.chercherConsultantRecruteurDisponibleParMois(moisCreneauRecherche)).thenReturn(consultantRecruteurs);
    }

    @Test
    public void should_planifier_entretien_when_everything_is_ok()
    {
        List<Compétence> compétencesCandidat = new ArrayList<>();
        compétencesCandidat.add(Compétence.AGILITE);

        //Creation du candidat
        Candidat candidat = Candidat.builder().Nom("Test").Competences(compétencesCandidat).build();

        //Creation du creneau horaire souhaité
        Calendar dateSouhaite = new GregorianCalendar(2019,3,5);
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(dateSouhaite).heureDebut(18).build();

        //On execute la logique métier
        planificationEntretiens.planifierEntretien(candidat,creneauHoraire);

        //On vérifie que l'entretien est persisté
        Entretien entretien = persistanceService.getEntretien(candidat);

        Assert.assertEquals(entretien.getCandidat(), candidat);
        Assert.assertEquals(entretien.getConsultantRecruteur().getNom(), "Consultant1");
        Assert.assertEquals(entretien.getCreneauHoraire(), creneauHoraire);
    }

    @Test(expected=UnMatchingCompetenceConsultantRecruteurException.class)
    public void should_not_planifier_entretien_when_ConsultantRecruteurs_have_not_competences()
    {
        List<Compétence> compétencesCandidat = new ArrayList<>();
        compétencesCandidat.add(Compétence.PYTHON);

        //Creation du candidat
        Candidat candidat = Candidat.builder().Nom("Test").Competences(compétencesCandidat).build();

        //Creation du creneau horaire souhaité
        Calendar dateSouhaite = new GregorianCalendar(2019,3,5);
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(dateSouhaite).heureDebut(18).build();

        //On execute la logique métier
        planificationEntretiens.planifierEntretien(candidat,creneauHoraire);

        //On vérifie qu'aucun entretien n'est persisté
        Entretien entretien = persistanceService.getEntretien(candidat);
    }

    @Test(expected=BusyConsultantRecruteurException.class)
    public void should_not_planifier_entretien_when_ConsultantRecruteur_is_busy()
     {
         List<Compétence> compétencesCandidat = new ArrayList<>();
         compétencesCandidat.add(Compétence.AGILITE);

         //Creation du candidat
         Candidat candidat = Candidat.builder().Nom("Test").Competences(compétencesCandidat).build();

         //Creation du creneau horaire souhaité
         Calendar dateSouhaite = new GregorianCalendar(2019,3,5);
         CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(dateSouhaite).heureDebut(13).build();

         //On execute la logique métier
         planificationEntretiens.planifierEntretien(candidat,creneauHoraire);

         //On vérifie qu'aucun entretien n'est persisté
         Entretien entretien = persistanceService.getEntretien(candidat);
     }

    @Test(expected=BusyConsultantRecruteurException.class)
    public void should_not_planifier_entretien_when_creneau_souhaite_is_before_working_time(){
        List<Compétence> compétencesCandidat = new ArrayList<>();
        compétencesCandidat.add(Compétence.JAVA);

        //Creation du candidat
        Candidat candidat = Candidat.builder().Nom("Test").Competences(compétencesCandidat).build();

        //Creation du creneau horaire souhaité
        Calendar dateSouhaite = new GregorianCalendar(2019,3,5);
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(dateSouhaite).heureDebut(10).build();

        //On execute la logique métier
        planificationEntretiens.planifierEntretien(candidat,creneauHoraire);

        //On vérifie qu'aucun entretien n'est persisté
       persistanceService.getEntretien(candidat);
    }

    @Test(expected=BusyConsultantRecruteurException.class)
    public void should_not_planifier_entretien_when_creneau_souhaite_is_after_working_time(){
        List<Compétence> compétencesCandidat = new ArrayList<>();
        compétencesCandidat.add(Compétence.JAVA);

        //Creation du candidat
        Candidat candidat = Candidat.builder().Nom("Test").Competences(compétencesCandidat).build();

        //Creation du creneau horaire souhaité
        Calendar dateSouhaite = new GregorianCalendar(2019,3,5);
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(dateSouhaite).heureDebut(22).build();

        //On execute la logique métier
        planificationEntretiens.planifierEntretien(candidat,creneauHoraire);

        //On vérifie qu'aucun entretien n'est persisté
        Entretien entretien = persistanceService.getEntretien(candidat);
    }

    @Test(expected=IncompleteInputParameterException.class)
    public void test_should_not_working_when_entretien_date_is_null(){
        List<Compétence> compétencesCandidat = new ArrayList<>();
        compétencesCandidat.add(Compétence.JAVA);

        //Creation du candidat
        Candidat candidat = Candidat.builder().Nom("Test").Competences(compétencesCandidat).build();

        //Creation du creneau horaire souhaité
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(null).heureDebut(22).build();

        //On execute la logique métier
        planificationEntretiens.planifierEntretien(candidat,creneauHoraire);

        //On vérifie qu'aucun entretien n'est persisté
        persistanceService.getEntretien(candidat);
    }

    @Test(expected=IncompleteInputParameterException.class)
    public void test_should_not_working_when_entretien_date_is_before_today(){
        List<Compétence> compétencesCandidat = new ArrayList<>();
        compétencesCandidat.add(Compétence.AGILITE);

        //Creation du candidat
        Candidat candidat = Candidat.builder().Nom("Test").Competences(compétencesCandidat).build();

        //Creation du creneau horaire souhaité
        Calendar dateSouhaite = new GregorianCalendar(2015,2,5);
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(dateSouhaite).heureDebut(18).build();

        //On execute la logique métier
        planificationEntretiens.planifierEntretien(candidat,creneauHoraire);

        //On vérifie qu'aucun entretien n'est persisté
        persistanceService.getEntretien(candidat);
    }

    @Test(expected=NoSkillsSelectedException.class)
    public void should_not_create_entretien_when_candidat_have_no_skills(){
        List<Compétence> compétencesCandidat = new ArrayList<>();

        //Creation du candidat
        Candidat candidat = Candidat.builder().Nom("Test").Competences(compétencesCandidat).build();

        //Creation du creneau horaire souhaité
        Calendar dateSouhaite = new GregorianCalendar(2019,3,5);
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(dateSouhaite).heureDebut(10).build();

        //On execute la logique métier
        planificationEntretiens.planifierEntretien(candidat,creneauHoraire);

        //On vérifie qu'aucun entretien n'est persisté
        persistanceService.getEntretien(candidat);
    }

    @Test(expected=NoSkillsSelectedException.class)
    public void should_not_create_entretien_when_candidat_skills_are_null(){
        //Creation du candidat
        Candidat candidat = Candidat.builder().Nom("Test").Competences(null).build();

        //Creation du creneau horaire souhaité
        Calendar dateSouhaite = new GregorianCalendar(2019,3,5);
        CreneauHoraire creneauHoraire = CreneauHoraire.builder().date(dateSouhaite).heureDebut(10).build();

        //On execute la logique métier
        planificationEntretiens.planifierEntretien(candidat,creneauHoraire);

        //On vérifie qu'aucun entretien n'est persisté
        persistanceService.getEntretien(candidat);
    }
}
