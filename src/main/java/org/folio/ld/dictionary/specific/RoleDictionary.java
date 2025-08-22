package org.folio.ld.dictionary.specific;

import static java.util.Optional.ofNullable;
import static org.folio.ld.dictionary.PredicateDictionary.ABRIDGER;
import static org.folio.ld.dictionary.PredicateDictionary.ACTOR;
import static org.folio.ld.dictionary.PredicateDictionary.ADAPTER;
import static org.folio.ld.dictionary.PredicateDictionary.ADDRESSEE;
import static org.folio.ld.dictionary.PredicateDictionary.ANALYST;
import static org.folio.ld.dictionary.PredicateDictionary.ANIMATOR;
import static org.folio.ld.dictionary.PredicateDictionary.ANNOTATOR;
import static org.folio.ld.dictionary.PredicateDictionary.ANNOUNCER;
import static org.folio.ld.dictionary.PredicateDictionary.APPELLANT;
import static org.folio.ld.dictionary.PredicateDictionary.APPELLEE;
import static org.folio.ld.dictionary.PredicateDictionary.APPLICANT;
import static org.folio.ld.dictionary.PredicateDictionary.ARCHITECT;
import static org.folio.ld.dictionary.PredicateDictionary.ARRANGER;
import static org.folio.ld.dictionary.PredicateDictionary.ARTIST;
import static org.folio.ld.dictionary.PredicateDictionary.ARTISTIC_DIRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.ART_COPYIST;
import static org.folio.ld.dictionary.PredicateDictionary.ART_DIRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.ASSIGNEE;
import static org.folio.ld.dictionary.PredicateDictionary.ASSOCIATED_NAME;
import static org.folio.ld.dictionary.PredicateDictionary.ATTRIBUTED_NAME;
import static org.folio.ld.dictionary.PredicateDictionary.AUCTIONEER;
import static org.folio.ld.dictionary.PredicateDictionary.AUDIO_ENGINEER;
import static org.folio.ld.dictionary.PredicateDictionary.AUTHOR;
import static org.folio.ld.dictionary.PredicateDictionary.AUTHOR_IN_QUOTATIONS_OR_TEXT_ABSTRACTS;
import static org.folio.ld.dictionary.PredicateDictionary.AUTHOR_OF_AFTERWORD_COLOPHON_ETC;
import static org.folio.ld.dictionary.PredicateDictionary.AUTHOR_OF_DIALOG;
import static org.folio.ld.dictionary.PredicateDictionary.AUTHOR_OF_INTRODUCTION_ETC;
import static org.folio.ld.dictionary.PredicateDictionary.AUTOGRAPHER;
import static org.folio.ld.dictionary.PredicateDictionary.BIBLIOGRAPHIC_ANTECEDENT;
import static org.folio.ld.dictionary.PredicateDictionary.BINDER;
import static org.folio.ld.dictionary.PredicateDictionary.BINDING_DESIGNER;
import static org.folio.ld.dictionary.PredicateDictionary.BLURB_WRITER;
import static org.folio.ld.dictionary.PredicateDictionary.BOOK_ARTIST;
import static org.folio.ld.dictionary.PredicateDictionary.BOOK_DESIGNER;
import static org.folio.ld.dictionary.PredicateDictionary.BOOK_JACKET_DESIGNER;
import static org.folio.ld.dictionary.PredicateDictionary.BOOK_PLATE_DESIGNER;
import static org.folio.ld.dictionary.PredicateDictionary.BOOK_PRODUCER;
import static org.folio.ld.dictionary.PredicateDictionary.BOOK_SELLER;
import static org.folio.ld.dictionary.PredicateDictionary.BRAILLE_EMBOSSER;
import static org.folio.ld.dictionary.PredicateDictionary.BROADCASTER;
import static org.folio.ld.dictionary.PredicateDictionary.CALLIGRAPHER;
import static org.folio.ld.dictionary.PredicateDictionary.CAMERA_OPERATOR;
import static org.folio.ld.dictionary.PredicateDictionary.CARTOGRAPHER;
import static org.folio.ld.dictionary.PredicateDictionary.CASTER;
import static org.folio.ld.dictionary.PredicateDictionary.CASTING_DIRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.CENSOR;
import static org.folio.ld.dictionary.PredicateDictionary.CHOREOGRAPHER;
import static org.folio.ld.dictionary.PredicateDictionary.CINEMATOGRAPHER;
import static org.folio.ld.dictionary.PredicateDictionary.CLIENT;
import static org.folio.ld.dictionary.PredicateDictionary.COLLABORATOR;
import static org.folio.ld.dictionary.PredicateDictionary.COLLECTION_REGISTRAR;
import static org.folio.ld.dictionary.PredicateDictionary.COLLECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.COLLOTYPER;
import static org.folio.ld.dictionary.PredicateDictionary.COLORIST;
import static org.folio.ld.dictionary.PredicateDictionary.COMMENTATOR;
import static org.folio.ld.dictionary.PredicateDictionary.COMMENTATOR_FOR_WRITTEN_TEXT;
import static org.folio.ld.dictionary.PredicateDictionary.COMPILER;
import static org.folio.ld.dictionary.PredicateDictionary.COMPLAINANT;
import static org.folio.ld.dictionary.PredicateDictionary.COMPLAINANT_APPELLANT;
import static org.folio.ld.dictionary.PredicateDictionary.COMPLAINANT_APPELLEE;
import static org.folio.ld.dictionary.PredicateDictionary.COMPOSER;
import static org.folio.ld.dictionary.PredicateDictionary.COMPOSITOR;
import static org.folio.ld.dictionary.PredicateDictionary.CONCEPTOR;
import static org.folio.ld.dictionary.PredicateDictionary.CONDUCTOR;
import static org.folio.ld.dictionary.PredicateDictionary.CONSERVATOR;
import static org.folio.ld.dictionary.PredicateDictionary.CONSULTANT;
import static org.folio.ld.dictionary.PredicateDictionary.CONSULTANT_TO_A_PROJECT;
import static org.folio.ld.dictionary.PredicateDictionary.CONTESTANT;
import static org.folio.ld.dictionary.PredicateDictionary.CONTESTANT_APPELLANT;
import static org.folio.ld.dictionary.PredicateDictionary.CONTESTANT_APPELLEE;
import static org.folio.ld.dictionary.PredicateDictionary.CONTESTEE;
import static org.folio.ld.dictionary.PredicateDictionary.CONTESTEE_APPELLANT;
import static org.folio.ld.dictionary.PredicateDictionary.CONTESTEE_APPELLEE;
import static org.folio.ld.dictionary.PredicateDictionary.CONTRACTOR;
import static org.folio.ld.dictionary.PredicateDictionary.COPYRIGHT_CLAIMANT;
import static org.folio.ld.dictionary.PredicateDictionary.COPYRIGHT_HOLDER;
import static org.folio.ld.dictionary.PredicateDictionary.CORRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.CORRESPONDENT;
import static org.folio.ld.dictionary.PredicateDictionary.COSTUME_DESIGNER;
import static org.folio.ld.dictionary.PredicateDictionary.COURT_GOVERNED;
import static org.folio.ld.dictionary.PredicateDictionary.COURT_REPORTER;
import static org.folio.ld.dictionary.PredicateDictionary.COVER_DESIGNER;
import static org.folio.ld.dictionary.PredicateDictionary.CURATOR;
import static org.folio.ld.dictionary.PredicateDictionary.DANCER;
import static org.folio.ld.dictionary.PredicateDictionary.DATA_CONTRIBUTOR;
import static org.folio.ld.dictionary.PredicateDictionary.DATA_MANAGER;
import static org.folio.ld.dictionary.PredicateDictionary.DEDICATEE;
import static org.folio.ld.dictionary.PredicateDictionary.DEDICATOR;
import static org.folio.ld.dictionary.PredicateDictionary.DEFENDANT;
import static org.folio.ld.dictionary.PredicateDictionary.DEFENDANT_APPELLANT;
import static org.folio.ld.dictionary.PredicateDictionary.DEFENDANT_APPELLEE;
import static org.folio.ld.dictionary.PredicateDictionary.DEGREE_COMMITTEE_MEMBER;
import static org.folio.ld.dictionary.PredicateDictionary.DEGREE_GRANTING_INSTITUTION;
import static org.folio.ld.dictionary.PredicateDictionary.DEGREE_SUPERVISOR;
import static org.folio.ld.dictionary.PredicateDictionary.DELINEATOR;
import static org.folio.ld.dictionary.PredicateDictionary.DEPICTED;
import static org.folio.ld.dictionary.PredicateDictionary.DESIGNER;
import static org.folio.ld.dictionary.PredicateDictionary.DIRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.DISSERTANT;
import static org.folio.ld.dictionary.PredicateDictionary.DISTRIBUTION_PLACE;
import static org.folio.ld.dictionary.PredicateDictionary.DISTRIBUTOR;
import static org.folio.ld.dictionary.PredicateDictionary.DJ;
import static org.folio.ld.dictionary.PredicateDictionary.DONOR;
import static org.folio.ld.dictionary.PredicateDictionary.DPT;
import static org.folio.ld.dictionary.PredicateDictionary.DRAFTSMAN;
import static org.folio.ld.dictionary.PredicateDictionary.DUBBING_DIRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.DUBIOUS_AUTHOR;
import static org.folio.ld.dictionary.PredicateDictionary.EDITOR;
import static org.folio.ld.dictionary.PredicateDictionary.EDITORIAL_DIRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.EDITOR_OF_COMPILATION;
import static org.folio.ld.dictionary.PredicateDictionary.EDITOR_OF_MOVING_IMAGE_WORK;
import static org.folio.ld.dictionary.PredicateDictionary.ELECTRICIAN;
import static org.folio.ld.dictionary.PredicateDictionary.ELECTRO_TYPER;
import static org.folio.ld.dictionary.PredicateDictionary.ENACTING_JURISDICTION;
import static org.folio.ld.dictionary.PredicateDictionary.ENGINEER;
import static org.folio.ld.dictionary.PredicateDictionary.ENGRAVER;
import static org.folio.ld.dictionary.PredicateDictionary.ETCHER;
import static org.folio.ld.dictionary.PredicateDictionary.EVENT_PLACE;
import static org.folio.ld.dictionary.PredicateDictionary.EXPERT;
import static org.folio.ld.dictionary.PredicateDictionary.FACSIMILIST;
import static org.folio.ld.dictionary.PredicateDictionary.FIELD_DIRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.FILMMAKER;
import static org.folio.ld.dictionary.PredicateDictionary.FILM_DIRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.FILM_DISTRIBUTOR;
import static org.folio.ld.dictionary.PredicateDictionary.FILM_EDITOR;
import static org.folio.ld.dictionary.PredicateDictionary.FILM_PRODUCER;
import static org.folio.ld.dictionary.PredicateDictionary.FIRST_PARTY;
import static org.folio.ld.dictionary.PredicateDictionary.FORGER;
import static org.folio.ld.dictionary.PredicateDictionary.FORMER_OWNER;
import static org.folio.ld.dictionary.PredicateDictionary.FOUNDER;
import static org.folio.ld.dictionary.PredicateDictionary.FUNDER;
import static org.folio.ld.dictionary.PredicateDictionary.GAME_DEVELOPER;
import static org.folio.ld.dictionary.PredicateDictionary.GEOGRAPHIC_INFORMATION_SPECIALIST;
import static org.folio.ld.dictionary.PredicateDictionary.GRAPHIC_TECHNICIAN;
import static org.folio.ld.dictionary.PredicateDictionary.HONOREE;
import static org.folio.ld.dictionary.PredicateDictionary.HOST;
import static org.folio.ld.dictionary.PredicateDictionary.HOST_INSTITUTION;
import static org.folio.ld.dictionary.PredicateDictionary.ILLUMINATOR;
import static org.folio.ld.dictionary.PredicateDictionary.ILLUSTRATOR;
import static org.folio.ld.dictionary.PredicateDictionary.INKER;
import static org.folio.ld.dictionary.PredicateDictionary.INSCRIBER;
import static org.folio.ld.dictionary.PredicateDictionary.INSTRUMENTALIST;
import static org.folio.ld.dictionary.PredicateDictionary.INTERVIEWEE;
import static org.folio.ld.dictionary.PredicateDictionary.INTERVIEWER;
import static org.folio.ld.dictionary.PredicateDictionary.INVENTOR;
import static org.folio.ld.dictionary.PredicateDictionary.ISSUING_BODY;
import static org.folio.ld.dictionary.PredicateDictionary.JUDGE;
import static org.folio.ld.dictionary.PredicateDictionary.JURISDICTION_GOVERNED;
import static org.folio.ld.dictionary.PredicateDictionary.LABORATORY;
import static org.folio.ld.dictionary.PredicateDictionary.LAB_DIRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.LANDSCAPE_ARCHITECT;
import static org.folio.ld.dictionary.PredicateDictionary.LEAD;
import static org.folio.ld.dictionary.PredicateDictionary.LENDER;
import static org.folio.ld.dictionary.PredicateDictionary.LETTERER;
import static org.folio.ld.dictionary.PredicateDictionary.LIBELANT;
import static org.folio.ld.dictionary.PredicateDictionary.LIBELANT_APPELLANT;
import static org.folio.ld.dictionary.PredicateDictionary.LIBELANT_APPELLEE;
import static org.folio.ld.dictionary.PredicateDictionary.LIBELEE;
import static org.folio.ld.dictionary.PredicateDictionary.LIBELEE_APPELLANT;
import static org.folio.ld.dictionary.PredicateDictionary.LIBELEE_APPELLEE;
import static org.folio.ld.dictionary.PredicateDictionary.LIBRETTIST;
import static org.folio.ld.dictionary.PredicateDictionary.LICENSEE;
import static org.folio.ld.dictionary.PredicateDictionary.LICENSOR;
import static org.folio.ld.dictionary.PredicateDictionary.LIGHTING_DESIGNER;
import static org.folio.ld.dictionary.PredicateDictionary.LITHOGRAPHER;
import static org.folio.ld.dictionary.PredicateDictionary.LYRICIST;
import static org.folio.ld.dictionary.PredicateDictionary.MAKEUP_ARTIST;
import static org.folio.ld.dictionary.PredicateDictionary.MANUFACTURER;
import static org.folio.ld.dictionary.PredicateDictionary.MANUFACTURE_PLACE;
import static org.folio.ld.dictionary.PredicateDictionary.MARBLER;
import static org.folio.ld.dictionary.PredicateDictionary.MARKUP_EDITOR;
import static org.folio.ld.dictionary.PredicateDictionary.MEDIUM;
import static org.folio.ld.dictionary.PredicateDictionary.METADATA_CONTACT;
import static org.folio.ld.dictionary.PredicateDictionary.METAL_ENGRAVER;
import static org.folio.ld.dictionary.PredicateDictionary.MINUTE_TAKER;
import static org.folio.ld.dictionary.PredicateDictionary.MIXING_ENGINEER;
import static org.folio.ld.dictionary.PredicateDictionary.MODERATOR;
import static org.folio.ld.dictionary.PredicateDictionary.MONITOR;
import static org.folio.ld.dictionary.PredicateDictionary.MUSICAL_DIRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.MUSICIAN;
import static org.folio.ld.dictionary.PredicateDictionary.MUSIC_COPYIST;
import static org.folio.ld.dictionary.PredicateDictionary.MUSIC_PROGRAMMER;
import static org.folio.ld.dictionary.PredicateDictionary.NARRATOR;
import static org.folio.ld.dictionary.PredicateDictionary.NEWS_ANCHOR;
import static org.folio.ld.dictionary.PredicateDictionary.ONSCREEN_PARTICIPANT;
import static org.folio.ld.dictionary.PredicateDictionary.ONSCREEN_PRESENTER;
import static org.folio.ld.dictionary.PredicateDictionary.OPPONENT;
import static org.folio.ld.dictionary.PredicateDictionary.ORGANIZER;
import static org.folio.ld.dictionary.PredicateDictionary.ORIGINATOR;
import static org.folio.ld.dictionary.PredicateDictionary.OTHER;
import static org.folio.ld.dictionary.PredicateDictionary.OWNER;
import static org.folio.ld.dictionary.PredicateDictionary.PANELIST;
import static org.folio.ld.dictionary.PredicateDictionary.PAPER_MAKER;
import static org.folio.ld.dictionary.PredicateDictionary.PATENT_APPLICANT;
import static org.folio.ld.dictionary.PredicateDictionary.PATENT_HOLDER;
import static org.folio.ld.dictionary.PredicateDictionary.PATRON;
import static org.folio.ld.dictionary.PredicateDictionary.PENCILLER;
import static org.folio.ld.dictionary.PredicateDictionary.PERFORMER;
import static org.folio.ld.dictionary.PredicateDictionary.PERMITTING_AGENCY;
import static org.folio.ld.dictionary.PredicateDictionary.PHOTOGRAPHER;
import static org.folio.ld.dictionary.PredicateDictionary.PLACE_OF_ADDRESS;
import static org.folio.ld.dictionary.PredicateDictionary.PLAINTIFF;
import static org.folio.ld.dictionary.PredicateDictionary.PLAINTIFF_APPELLANT;
import static org.folio.ld.dictionary.PredicateDictionary.PLAINTIFF_APPELLEE;
import static org.folio.ld.dictionary.PredicateDictionary.PLATE_MAKER;
import static org.folio.ld.dictionary.PredicateDictionary.PRAESES;
import static org.folio.ld.dictionary.PredicateDictionary.PRESENTER;
import static org.folio.ld.dictionary.PredicateDictionary.PRINTER;
import static org.folio.ld.dictionary.PredicateDictionary.PRINTER_OF_PLATES;
import static org.folio.ld.dictionary.PredicateDictionary.PRINT_MAKER;
import static org.folio.ld.dictionary.PredicateDictionary.PROCESS_CONTACT;
import static org.folio.ld.dictionary.PredicateDictionary.PRODUCER;
import static org.folio.ld.dictionary.PredicateDictionary.PRODUCTION_COMPANY;
import static org.folio.ld.dictionary.PredicateDictionary.PRODUCTION_DESIGNER;
import static org.folio.ld.dictionary.PredicateDictionary.PRODUCTION_MANAGER;
import static org.folio.ld.dictionary.PredicateDictionary.PRODUCTION_PERSONNEL;
import static org.folio.ld.dictionary.PredicateDictionary.PRODUCTION_PLACE;
import static org.folio.ld.dictionary.PredicateDictionary.PROGRAMMER;
import static org.folio.ld.dictionary.PredicateDictionary.PROJECT_DIRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.PROOFREADER;
import static org.folio.ld.dictionary.PredicateDictionary.PROVIDER;
import static org.folio.ld.dictionary.PredicateDictionary.PUBLICATION_PLACE;
import static org.folio.ld.dictionary.PredicateDictionary.PUBLISHER;
import static org.folio.ld.dictionary.PredicateDictionary.PUBLISHING_DIRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.PUPPETEER;
import static org.folio.ld.dictionary.PredicateDictionary.RADIO_DIRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.RADIO_PRODUCER;
import static org.folio.ld.dictionary.PredicateDictionary.RAPPORTEUR;
import static org.folio.ld.dictionary.PredicateDictionary.RECORDING_ENGINEER;
import static org.folio.ld.dictionary.PredicateDictionary.RECORDIST;
import static org.folio.ld.dictionary.PredicateDictionary.REDAKTOR;
import static org.folio.ld.dictionary.PredicateDictionary.REMIX_ARTIST;
import static org.folio.ld.dictionary.PredicateDictionary.RENDERER;
import static org.folio.ld.dictionary.PredicateDictionary.REPORTER;
import static org.folio.ld.dictionary.PredicateDictionary.REPOSITORY;
import static org.folio.ld.dictionary.PredicateDictionary.RESEARCHER;
import static org.folio.ld.dictionary.PredicateDictionary.RESEARCH_TEAM_HEAD;
import static org.folio.ld.dictionary.PredicateDictionary.RESEARCH_TEAM_MEMBER;
import static org.folio.ld.dictionary.PredicateDictionary.RESPONDENT;
import static org.folio.ld.dictionary.PredicateDictionary.RESPONDENT_APPELLANT;
import static org.folio.ld.dictionary.PredicateDictionary.RESPONDENT_APPELLEE;
import static org.folio.ld.dictionary.PredicateDictionary.RESPONSIBLE_PARTY;
import static org.folio.ld.dictionary.PredicateDictionary.RESTAGER;
import static org.folio.ld.dictionary.PredicateDictionary.RESTORATIONIST;
import static org.folio.ld.dictionary.PredicateDictionary.REVIEWER;
import static org.folio.ld.dictionary.PredicateDictionary.RUBRICATOR;
import static org.folio.ld.dictionary.PredicateDictionary.SCENARIST;
import static org.folio.ld.dictionary.PredicateDictionary.SCIENTIFIC_ADVISOR;
import static org.folio.ld.dictionary.PredicateDictionary.SCREENWRITER;
import static org.folio.ld.dictionary.PredicateDictionary.SCRIBE;
import static org.folio.ld.dictionary.PredicateDictionary.SCULPTOR;
import static org.folio.ld.dictionary.PredicateDictionary.SECOND_PARTY;
import static org.folio.ld.dictionary.PredicateDictionary.SECRETARY;
import static org.folio.ld.dictionary.PredicateDictionary.SELLER;
import static org.folio.ld.dictionary.PredicateDictionary.SETTING;
import static org.folio.ld.dictionary.PredicateDictionary.SET_DESIGNER;
import static org.folio.ld.dictionary.PredicateDictionary.SIGNER;
import static org.folio.ld.dictionary.PredicateDictionary.SINGER;
import static org.folio.ld.dictionary.PredicateDictionary.SOFTWARE_DEVELOPER;
import static org.folio.ld.dictionary.PredicateDictionary.SOUND_DESIGNER;
import static org.folio.ld.dictionary.PredicateDictionary.SOUND_ENGINEER;
import static org.folio.ld.dictionary.PredicateDictionary.SPEAKER;
import static org.folio.ld.dictionary.PredicateDictionary.SPECIAL_EFFECTS_PROVIDER;
import static org.folio.ld.dictionary.PredicateDictionary.SPONSOR;
import static org.folio.ld.dictionary.PredicateDictionary.STAGE_DIRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.STAGE_MANAGER;
import static org.folio.ld.dictionary.PredicateDictionary.STANDARDS_BODY;
import static org.folio.ld.dictionary.PredicateDictionary.STEREOTYPER;
import static org.folio.ld.dictionary.PredicateDictionary.STORYTELLER;
import static org.folio.ld.dictionary.PredicateDictionary.SUPPORTING_HOST;
import static org.folio.ld.dictionary.PredicateDictionary.SURVEYOR;
import static org.folio.ld.dictionary.PredicateDictionary.TEACHER;
import static org.folio.ld.dictionary.PredicateDictionary.TECHNICAL_ADVISOR;
import static org.folio.ld.dictionary.PredicateDictionary.TECHNICAL_DIRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.TELEVISION_DIRECTOR;
import static org.folio.ld.dictionary.PredicateDictionary.TELEVISION_GUEST;
import static org.folio.ld.dictionary.PredicateDictionary.TELEVISION_HOST;
import static org.folio.ld.dictionary.PredicateDictionary.TELEVISION_PRODUCER;
import static org.folio.ld.dictionary.PredicateDictionary.TELEVISION_WRITER;
import static org.folio.ld.dictionary.PredicateDictionary.THESIS_ADVISOR;
import static org.folio.ld.dictionary.PredicateDictionary.TRANSCRIBER;
import static org.folio.ld.dictionary.PredicateDictionary.TRANSLATOR;
import static org.folio.ld.dictionary.PredicateDictionary.TYPE_DESIGNER;
import static org.folio.ld.dictionary.PredicateDictionary.TYPOGRAPHER;
import static org.folio.ld.dictionary.PredicateDictionary.UNIVERSITY_PLACE;
import static org.folio.ld.dictionary.PredicateDictionary.VIDEOGRAPHER;
import static org.folio.ld.dictionary.PredicateDictionary.VISUAL_EFFECTS_PROVIDER;
import static org.folio.ld.dictionary.PredicateDictionary.VOCALIST;
import static org.folio.ld.dictionary.PredicateDictionary.VOICE_ACTOR;
import static org.folio.ld.dictionary.PredicateDictionary.WITNESS;
import static org.folio.ld.dictionary.PredicateDictionary.WOODCUTTER;
import static org.folio.ld.dictionary.PredicateDictionary.WOOD_ENGRAVER;
import static org.folio.ld.dictionary.PredicateDictionary.WRITER_OF_ACCOMPANYING_MATERIAL;
import static org.folio.ld.dictionary.PredicateDictionary.WRITER_OF_ADDED_COMMENTARY;
import static org.folio.ld.dictionary.PredicateDictionary.WRITER_OF_ADDED_LYRICS;
import static org.folio.ld.dictionary.PredicateDictionary.WRITER_OF_ADDED_TEXT;
import static org.folio.ld.dictionary.PredicateDictionary.WRITER_OF_AFTERWORD;
import static org.folio.ld.dictionary.PredicateDictionary.WRITER_OF_FILM_STORY;
import static org.folio.ld.dictionary.PredicateDictionary.WRITER_OF_FOREWORD;
import static org.folio.ld.dictionary.PredicateDictionary.WRITER_OF_INTERTITLES;
import static org.folio.ld.dictionary.PredicateDictionary.WRITER_OF_INTRODUCTION;
import static org.folio.ld.dictionary.PredicateDictionary.WRITER_OF_PREFACE;
import static org.folio.ld.dictionary.PredicateDictionary.WRITER_OF_SUPPLEMENTARY_TEXTUAL_CONTENT;
import static org.folio.ld.dictionary.PredicateDictionary.WRITER_OF_TELEVISION_STORY;

import com.google.common.collect.ImmutableBiMap;
import java.util.Optional;
import org.folio.ld.dictionary.PredicateDictionary;

public final class RoleDictionary {
  private static final ImmutableBiMap<String, PredicateDictionary> VALUE_MAP =
    new ImmutableBiMap.Builder<String, PredicateDictionary>()
      .put("abr", ABRIDGER)
      .put("acp", ART_COPYIST)
      .put("act", ACTOR)
      .put("adi", ART_DIRECTOR)
      .put("adp", ADAPTER)
      .put("aft", AUTHOR_OF_AFTERWORD_COLOPHON_ETC)
      .put("anc", ANNOUNCER)
      .put("anl", ANALYST)
      .put("anm", ANIMATOR)
      .put("ann", ANNOTATOR)
      .put("ant", BIBLIOGRAPHIC_ANTECEDENT)
      .put("ape", APPELLEE)
      .put("apl", APPELLANT)
      .put("app", APPLICANT)
      .put("aqt", AUTHOR_IN_QUOTATIONS_OR_TEXT_ABSTRACTS)
      .put("arc", ARCHITECT)
      .put("ard", ARTISTIC_DIRECTOR)
      .put("arr", ARRANGER)
      .put("art", ARTIST)
      .put("asg", ASSIGNEE)
      .put("asn", ASSOCIATED_NAME)
      .put("ato", AUTOGRAPHER)
      .put("att", ATTRIBUTED_NAME)
      .put("auc", AUCTIONEER)
      .put("aud", AUTHOR_OF_DIALOG)
      .put("aue", AUDIO_ENGINEER)
      .put("aui", AUTHOR_OF_INTRODUCTION_ETC)
      .put("aus", SCREENWRITER)
      .put("aut", AUTHOR)
      .put("bdd", BINDING_DESIGNER)
      .put("bjd", BOOK_JACKET_DESIGNER)
      .put("bka", BOOK_ARTIST)
      .put("bkd", BOOK_DESIGNER)
      .put("bkp", BOOK_PRODUCER)
      .put("blw", BLURB_WRITER)
      .put("bnd", BINDER)
      .put("bpd", BOOK_PLATE_DESIGNER)
      .put("brd", BROADCASTER)
      .put("brl", BRAILLE_EMBOSSER)
      .put("bsl", BOOK_SELLER)
      .put("cad", CASTING_DIRECTOR)
      .put("cas", CASTER)
      .put("ccp", CONCEPTOR)
      .put("chr", CHOREOGRAPHER)
      .put("clb", COLLABORATOR)
      .put("cli", CLIENT)
      .put("cll", CALLIGRAPHER)
      .put("clr", COLORIST)
      .put("clt", COLLOTYPER)
      .put("cmm", COMMENTATOR)
      .put("cmp", COMPOSER)
      .put("cmt", COMPOSITOR)
      .put("cnd", CONDUCTOR)
      .put("cng", CINEMATOGRAPHER)
      .put("cns", CENSOR)
      .put("coe", CONTESTANT_APPELLEE)
      .put("col", COLLECTOR)
      .put("com", COMPILER)
      .put("con", CONSERVATOR)
      .put("cop", CAMERA_OPERATOR)
      .put("cor", COLLECTION_REGISTRAR)
      .put("cos", CONTESTANT)
      .put("cot", CONTESTANT_APPELLANT)
      .put("cou", COURT_GOVERNED)
      .put("cov", COVER_DESIGNER)
      .put("cpc", COPYRIGHT_CLAIMANT)
      .put("cpe", COMPLAINANT_APPELLEE)
      .put("cph", COPYRIGHT_HOLDER)
      .put("cpl", COMPLAINANT)
      .put("cpt", COMPLAINANT_APPELLANT)
      .put("crp", CORRESPONDENT)
      .put("crr", CORRECTOR)
      .put("crt", COURT_REPORTER)
      .put("csl", CONSULTANT)
      .put("csp", CONSULTANT_TO_A_PROJECT)
      .put("cst", COSTUME_DESIGNER)
      .put("cte", CONTESTEE_APPELLEE)
      .put("ctg", CARTOGRAPHER)
      .put("ctr", CONTRACTOR)
      .put("cts", CONTESTEE)
      .put("ctt", CONTESTEE_APPELLANT)
      .put("cur", CURATOR)
      .put("cwt", COMMENTATOR_FOR_WRITTEN_TEXT)
      .put("dbd", DUBBING_DIRECTOR)
      .put("dbp", DISTRIBUTION_PLACE)
      .put("dfd", DEFENDANT)
      .put("dfe", DEFENDANT_APPELLEE)
      .put("dft", DEFENDANT_APPELLANT)
      .put("dgc", DEGREE_COMMITTEE_MEMBER)
      .put("dgg", DEGREE_GRANTING_INSTITUTION)
      .put("dgs", DEGREE_SUPERVISOR)
      .put("dis", DISSERTANT)
      .put("djo", DJ)
      .put("dln", DELINEATOR)
      .put("dnc", DANCER)
      .put("dnr", DONOR)
      .put("dpc", DEPICTED)
      .put("dpt", DPT)
      .put("drm", DRAFTSMAN)
      .put("drt", DIRECTOR)
      .put("dsr", DESIGNER)
      .put("dst", DISTRIBUTOR)
      .put("dtc", DATA_CONTRIBUTOR)
      .put("dte", DEDICATEE)
      .put("dtm", DATA_MANAGER)
      .put("dto", DEDICATOR)
      .put("dub", DUBIOUS_AUTHOR)
      .put("edc", EDITOR_OF_COMPILATION)
      .put("edd", EDITORIAL_DIRECTOR)
      .put("edm", EDITOR_OF_MOVING_IMAGE_WORK)
      .put("edt", EDITOR)
      .put("egr", ENGRAVER)
      .put("elg", ELECTRICIAN)
      .put("elt", ELECTRO_TYPER)
      .put("eng", ENGINEER)
      .put("enj", ENACTING_JURISDICTION)
      .put("etr", ETCHER)
      .put("evp", EVENT_PLACE)
      .put("exp", EXPERT)
      .put("fac", FACSIMILIST)
      .put("fds", FILM_DISTRIBUTOR)
      .put("fld", FIELD_DIRECTOR)
      .put("flm", FILM_EDITOR)
      .put("fmd", FILM_DIRECTOR)
      .put("fmk", FILMMAKER)
      .put("fmo", FORMER_OWNER)
      .put("fmp", FILM_PRODUCER)
      .put("fnd", FUNDER)
      .put("fon", FOUNDER)
      .put("fpy", FIRST_PARTY)
      .put("frg", FORGER)
      .put("gdv", GAME_DEVELOPER)
      .put("gis", GEOGRAPHIC_INFORMATION_SPECIALIST)
      .put("grt", GRAPHIC_TECHNICIAN)
      .put("his", HOST_INSTITUTION)
      .put("hnr", HONOREE)
      .put("hst", HOST)
      .put("ill", ILLUSTRATOR)
      .put("ilu", ILLUMINATOR)
      .put("ink", INKER)
      .put("ins", INSCRIBER)
      .put("inv", INVENTOR)
      .put("isb", ISSUING_BODY)
      .put("itr", INSTRUMENTALIST)
      .put("ive", INTERVIEWEE)
      .put("ivr", INTERVIEWER)
      .put("jud", JUDGE)
      .put("jug", JURISDICTION_GOVERNED)
      .put("lbr", LABORATORY)
      .put("lbt", LIBRETTIST)
      .put("ldr", LAB_DIRECTOR)
      .put("led", LEAD)
      .put("lee", LIBELEE_APPELLEE)
      .put("lel", LIBELEE)
      .put("len", LENDER)
      .put("let", LIBELEE_APPELLANT)
      .put("lgd", LIGHTING_DESIGNER)
      .put("lie", LIBELANT_APPELLEE)
      .put("lil", LIBELANT)
      .put("lit", LIBELANT_APPELLANT)
      .put("lsa", LANDSCAPE_ARCHITECT)
      .put("lse", LICENSEE)
      .put("lso", LICENSOR)
      .put("ltg", LITHOGRAPHER)
      .put("ltr", LETTERER)
      .put("lyr", LYRICIST)
      .put("mcp", MUSIC_COPYIST)
      .put("mdc", METADATA_CONTACT)
      .put("med", MEDIUM)
      .put("mfp", MANUFACTURE_PLACE)
      .put("mfr", MANUFACTURER)
      .put("mka", MAKEUP_ARTIST)
      .put("mod", MODERATOR)
      .put("mon", MONITOR)
      .put("mrb", MARBLER)
      .put("mrk", MARKUP_EDITOR)
      .put("msd", MUSICAL_DIRECTOR)
      .put("mte", METAL_ENGRAVER)
      .put("mtk", MINUTE_TAKER)
      .put("mup", MUSIC_PROGRAMMER)
      .put("mus", MUSICIAN)
      .put("mxe", MIXING_ENGINEER)
      .put("nan", NEWS_ANCHOR)
      .put("nrt", NARRATOR)
      .put("onp", ONSCREEN_PARTICIPANT)
      .put("opn", OPPONENT)
      .put("org", ORIGINATOR)
      .put("orm", ORGANIZER)
      .put("osp", ONSCREEN_PRESENTER)
      .put("oth", OTHER)
      .put("own", OWNER)
      .put("pad", PLACE_OF_ADDRESS)
      .put("pan", PANELIST)
      .put("pat", PATRON)
      .put("pbd", PUBLISHING_DIRECTOR)
      .put("pbl", PUBLISHER)
      .put("pdr", PROJECT_DIRECTOR)
      .put("pfr", PROOFREADER)
      .put("pht", PHOTOGRAPHER)
      .put("plt", PLATE_MAKER)
      .put("pma", PERMITTING_AGENCY)
      .put("pmn", PRODUCTION_MANAGER)
      .put("pnc", PENCILLER)
      .put("pop", PRINTER_OF_PLATES)
      .put("ppm", PAPER_MAKER)
      .put("ppt", PUPPETEER)
      .put("pra", PRAESES)
      .put("prc", PROCESS_CONTACT)
      .put("prd", PRODUCTION_PERSONNEL)
      .put("pre", PRESENTER)
      .put("prf", PERFORMER)
      .put("prg", PROGRAMMER)
      .put("prm", PRINT_MAKER)
      .put("prn", PRODUCTION_COMPANY)
      .put("pro", PRODUCER)
      .put("prp", PRODUCTION_PLACE)
      .put("prs", PRODUCTION_DESIGNER)
      .put("prt", PRINTER)
      .put("prv", PROVIDER)
      .put("pta", PATENT_APPLICANT)
      .put("pte", PLAINTIFF_APPELLEE)
      .put("ptf", PLAINTIFF)
      .put("pth", PATENT_HOLDER)
      .put("ptt", PLAINTIFF_APPELLANT)
      .put("pup", PUBLICATION_PLACE)
      .put("rap", RAPPORTEUR)
      .put("rbr", RUBRICATOR)
      .put("rcd", RECORDIST)
      .put("rce", RECORDING_ENGINEER)
      .put("rcp", ADDRESSEE)
      .put("rdd", RADIO_DIRECTOR)
      .put("red", REDAKTOR)
      .put("ren", RENDERER)
      .put("res", RESEARCHER)
      .put("rev", REVIEWER)
      .put("rpc", RADIO_PRODUCER)
      .put("rps", REPOSITORY)
      .put("rpt", REPORTER)
      .put("rpy", RESPONSIBLE_PARTY)
      .put("rse", RESPONDENT_APPELLEE)
      .put("rsg", RESTAGER)
      .put("rsp", RESPONDENT)
      .put("rsr", RESTORATIONIST)
      .put("rst", RESPONDENT_APPELLANT)
      .put("rth", RESEARCH_TEAM_HEAD)
      .put("rtm", RESEARCH_TEAM_MEMBER)
      .put("rxa", REMIX_ARTIST)
      .put("sad", SCIENTIFIC_ADVISOR)
      .put("sce", SCENARIST)
      .put("scl", SCULPTOR)
      .put("scr", SCRIBE)
      .put("sde", SOUND_ENGINEER)
      .put("sds", SOUND_DESIGNER)
      .put("sec", SECRETARY)
      .put("sfx", SPECIAL_EFFECTS_PROVIDER)
      .put("sgd", STAGE_DIRECTOR)
      .put("sgn", SIGNER)
      .put("sht", SUPPORTING_HOST)
      .put("sll", SELLER)
      .put("sng", SINGER)
      .put("spk", SPEAKER)
      .put("spn", SPONSOR)
      .put("spy", SECOND_PARTY)
      .put("srv", SURVEYOR)
      .put("std", SET_DESIGNER)
      .put("stg", SETTING)
      .put("stl", STORYTELLER)
      .put("stm", STAGE_MANAGER)
      .put("stn", STANDARDS_BODY)
      .put("str", STEREOTYPER)
      .put("swd", SOFTWARE_DEVELOPER)
      .put("tad", TECHNICAL_ADVISOR)
      .put("tau", TELEVISION_WRITER)
      .put("tcd", TECHNICAL_DIRECTOR)
      .put("tch", TEACHER)
      .put("ths", THESIS_ADVISOR)
      .put("tld", TELEVISION_DIRECTOR)
      .put("tlg", TELEVISION_GUEST)
      .put("tlh", TELEVISION_HOST)
      .put("tlp", TELEVISION_PRODUCER)
      .put("trc", TRANSCRIBER)
      .put("trl", TRANSLATOR)
      .put("tyd", TYPE_DESIGNER)
      .put("tyg", TYPOGRAPHER)
      .put("uvp", UNIVERSITY_PLACE)
      .put("vac", VOICE_ACTOR)
      .put("vdg", VIDEOGRAPHER)
      .put("vfx", VISUAL_EFFECTS_PROVIDER)
      .put("voc", VOCALIST)
      .put("wac", WRITER_OF_ADDED_COMMENTARY)
      .put("wal", WRITER_OF_ADDED_LYRICS)
      .put("wam", WRITER_OF_ACCOMPANYING_MATERIAL)
      .put("wat", WRITER_OF_ADDED_TEXT)
      .put("waw", WRITER_OF_AFTERWORD)
      .put("wdc", WOODCUTTER)
      .put("wde", WOOD_ENGRAVER)
      .put("wfs", WRITER_OF_FILM_STORY)
      .put("wft", WRITER_OF_INTERTITLES)
      .put("wfw", WRITER_OF_FOREWORD)
      .put("win", WRITER_OF_INTRODUCTION)
      .put("wit", WITNESS)
      .put("wpr", WRITER_OF_PREFACE)
      .put("wst", WRITER_OF_SUPPLEMENTARY_TEXTUAL_CONTENT)
      .put("wts", WRITER_OF_TELEVISION_STORY)
      .build();

  private RoleDictionary() {
  }

  public static Optional<PredicateDictionary> getValue(String code) {
    return ofNullable(VALUE_MAP.get(code));
  }

  public static Optional<String> getCode(PredicateDictionary role) {
    return ofNullable(VALUE_MAP.inverse().get(role));
  }
}
