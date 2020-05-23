# DB scripts

CREATE TABLE hands
(
    hand          VARCHAR2 (100),
    position      VARCHAR2 (100),
    vs            VARCHAR2 (100),
    action        VARCHAR2 (100),
    PERCENTAGE    NUMBER,
    result        VARCHAR2 (100)
);

CREATE TABLE cards
(
    card    VARCHAR2 (100),
    RANK    NUMBER
);


CREATE TABLE suits
(
    suit    VARCHAR2 (100)
);

CREATE OR REPLACE VIEW v_cards
AS
    SELECT h1.card || h2.card || s.suit     hand,
           h1.RANK + h2.RANK                RANK,
           h1.card                          h1,
           h1.RANK                          h1_rank,
           h2.card                          h2,
           h2.RANK                          h2_rank,
           s.suit                           s
      FROM cards h1, cards h2, suits s
     WHERE h1.RANK >= h2.RANK AND h1.card != h2.card
    UNION
    SELECT hand,
           r,
           h1,
           h1_rank,
           h2,
           h2_rank,
           s
      FROM (SELECT DISTINCT h1.card || h2.card     hand,
                            h1.RANK,
                            h1.RANK + h2.RANK      r,
                            h1.card                h1,
                            h1.RANK                h1_rank,
                            h2.card                h2,
                            h2.RANK                h2_rank,
                            s.suit                 s
              FROM cards h1, cards h2, suits s
             WHERE h1.card = h2.card)
    ORDER BY RANK DESC;



CREATE OR REPLACE PACKAGE w2do
    AUTHID DEFINER
AS
    PROCEDURE add (position     VARCHAR2,
                   hh           VARCHAR2,
                   rank_down    NUMBER,
                   rank_right   NUMBER,
                   vs           VARCHAR2,
                   action       VARCHAR2,
                   percentage   NUMBER,
                   result       VARCHAR2);

    PROCEDURE add1 (POSITION     VARCHAR2,
                    HAND         VARCHAR2,
                    VS           VARCHAR2,
                    ACTION       VARCHAR2,
                    PERCENTAGE   NUMBER,
                    RESULT       VARCHAR2);
END w2do;
/

CREATE OR REPLACE PACKAGE BODY w2do
AS
    PROCEDURE add (position     VARCHAR2,
                   hh           VARCHAR2,
                   rank_down    NUMBER,
                   rank_right   NUMBER,
                   vs           VARCHAR2,
                   action       VARCHAR2,
                   percentage   NUMBER,
                   result       VARCHAR2)
    IS
    BEGIN
        FOR hs IN (SELECT hand
                     FROM v_cards
                    WHERE h1 = hh AND s = 'o' AND h2_rank >= rank_down
                   UNION
                   SELECT hand
                     FROM v_cards
                    WHERE h1 = hh AND s = 's' AND h2_rank >= rank_right)
        LOOP
            INSERT INTO HANDS (HAND,
                               POSITION,
                               VS,
                               ACTION,
                               PERCENTAGE,
                               RESULT)
                 VALUES (hs.hand,
                         position,
                         vs,
                         action,
                         percentage,
                         result);
        END LOOP;

        COMMIT;
    END;

    PROCEDURE add1 (POSITION     VARCHAR2,
                    HAND         VARCHAR2,
                    VS           VARCHAR2,
                    ACTION       VARCHAR2,
                    PERCENTAGE   NUMBER,
                    RESULT       VARCHAR2)
    IS
    BEGIN
        INSERT INTO HANDS (HAND,
                           POSITION,
                           VS,
                           ACTION,
                           PERCENTAGE,
                           RESULT)
             VALUES (HAND,
                     POSITION,
                     VS,
                     ACTION,
                     PERCENTAGE,
                     RESULT);

        COMMIT;
    END;
END w2do;
/


select '{"data" : [' from dual
union all
SELECT    '{'
       || '"hand": "'
       || hand
       || '",'
       || '"position": "'
       || position
       || '",'
       || '"vs": "'
       || vs
       || '",'
       || '"action": "'
       || action
       || '",'
       || '"PERCENTAGE": "'
       || percentage
       || '",'
       || '"result": "'
       || result
       || '"'
       || '},'    json
  FROM hands
  union all
  select ']}' from dual;
  
  
  src/main/java/main/data.json
  
  
  ----
  
  SQLlite
  
  CREATE TABLE HANDS
(
  HAND        TEXT ,
  POSITION   TEXT ,
  VS        TEXT ,
  ACTION     TEXT ,
  PERCENTAGE  INTEGER ,
  RESULT     TEXT 
)

select 'insert into hands values ('''||hand||''','''||position||''','''||vs||''','''||action||''','''||percentage||''','''||result||''');' from hands;

select 'list.add(new Record("'||hand||'","'||position||'","'||vs||'","'||action||'",'||percentage||',"'||result||'"));' from hands;


    w2do.add_bulk ('BTN','CO','RAISE',100,'vs RFI','');
	w2do.add_bulk ('BTN','CO','RAISE',75,'vs RFI','');
	w2do.add_bulk ('BTN','CO','RAISE',50,'vs RFI','');
	w2do.add_bulk ('BTN','CO','RAISE',25,'vs RFI','');
    w2do.add_bulk ('BTN','CO','CALL',100,'vs RFI','');
	
    w2do.add_bulk ('BTN','CO','CALL',75,'vs RFI','');
    w2do.add_bulk ('BTN','CO','CALL',50,'vs RFI','');
    w2do.add_bulk ('BTN','CO','CALL',25,'vs RFI','');    
    
    ---------------------------------------------------------------------------------------------------------------------------------
    
    # Data generation scripts
    
    BEGIN

    --- RFI
    w2do.add ('UTG','A',10,2,NULL,'RAISE',100,'RFI');
    w2do.add ('UTG','K',11,8,NULL,'RAISE',100,'RFI');
    w2do.add ('UTG','Q',12,9,NULL,'RAISE',100,'RFI');
    w2do.add ('UTG','J',11,9,NULL,'RAISE',100,'RFI');
    w2do.add ('UTG','T',10,9,NULL,'RAISE',100,'RFI');
    w2do.add ('UTG','9',9,9,NULL,'RAISE',100,'RFI');
    w2do.add1 ('UTG','98s',NULL,'RAISE',50,'RFI');
    w2do.add1 ('UTG','88',NULL,'RAISE',100,'RFI');
    w2do.add1 ('UTG','87s',NULL,'RAISE',50,'RFI');
    w2do.add1 ('UTG','77',NULL,'RAISE',100,'RFI');
    w2do.add1 ('UTG','76s',NULL,'RAISE',50,'RFI');
    w2do.add1 ('UTG','66',NULL,'RAISE',100,'RFI');
    w2do.add1 ('UTG','65s',NULL,'RAISE',50,'RFI');
    w2do.add1 ('UTG','55',NULL,'RAISE',100,'RFI');
    
    w2do.add ('HJ','A',10,2,NULL,'RAISE',100,'RFI');
    w2do.add ('HJ','K',11,7,NULL,'RAISE',100,'RFI');
    w2do.add ('HJ','Q',11,9,NULL,'RAISE',100,'RFI');
    w2do.add ('HJ','J',11,9,NULL,'RAISE',100,'RFI');
    w2do.add ('HJ','T',10,8,NULL,'RAISE',100,'RFI');
    w2do.add ('HJ','9',9,8,NULL,'RAISE',100,'RFI');
    w2do.add ('HJ','8',8,7,NULL,'RAISE',100,'RFI');
    w2do.add ('HJ','7',7,6,NULL,'RAISE',100,'RFI');
    w2do.add ('HJ','6',6,5,NULL,'RAISE',100,'RFI');
    w2do.add ('HJ','5',5,4,NULL,'RAISE',100,'RFI');
    w2do.add1 ('HJ','44',NULL,'RAISE',100,'RFI');
    w2do.add1 ('HJ','33',NULL,'RAISE',100,'RFI');
    
    w2do.add ('CO','A',8,2,NULL,'RAISE',100,'RFI');
    w2do.add ('CO','K',10,3,NULL,'RAISE',100,'RFI');
    w2do.add ('CO','Q',10,6,NULL,'RAISE',100,'RFI');
    w2do.add ('CO','J',10,7,NULL,'RAISE',100,'RFI');
    w2do.add ('CO','T',10,7,NULL,'RAISE',100,'RFI');
    w2do.add ('CO','9',9,7,NULL,'RAISE',100,'RFI');
    w2do.add ('CO','8',8,6,NULL,'RAISE',100,'RFI');
    w2do.add ('CO','7',7,6,NULL,'RAISE',100,'RFI');
    w2do.add ('CO','6',6,5,NULL,'RAISE',100,'RFI');
    w2do.add ('CO','5',5,4,NULL,'RAISE',100,'RFI');
    w2do.add1 ('CO','44',NULL,'RAISE',100,'RFI');
    w2do.add1 ('CO','33',NULL,'RAISE',100,'RFI');
    w2do.add1 ('CO','22',NULL,'RAISE',100,'RFI');
    
    w2do.add ('BTN','A',4,2,NULL,'RAISE',100,'RFI');
    w2do.add ('BTN','K',8,2,NULL,'RAISE',100,'RFI');
    w2do.add ('BTN','Q',9,2,NULL,'RAISE',100,'RFI');
    w2do.add ('BTN','J',9,4,NULL,'RAISE',100,'RFI');
    w2do.add1 ('BTN','J8o',NULL,'RAISE',50,'RFI');
    w2do.add ('BTN','T',8,6,NULL,'RAISE',100,'RFI');
    w2do.add ('BTN','9',8,6,NULL,'RAISE',100,'RFI');
    w2do.add ('BTN','8',8,5,NULL,'RAISE',100,'RFI');
    w2do.add ('BTN','7',7,5,NULL,'RAISE',100,'RFI');
    w2do.add ('BTN','6',6,4,NULL,'RAISE',100,'RFI');
    w2do.add ('BTN','5',5,4,NULL,'RAISE',100,'RFI');
    w2do.add1 ('BTN','44',NULL,'RAISE',100,'RFI');
    w2do.add1 ('BTN','33',NULL,'RAISE',100,'RFI');
    w2do.add1 ('BTN','22',NULL,'RAISE',100,'RFI');
    
    w2do.add ('SB','A',4,2,NULL,'RAISE',100,'RFI');
    w2do.add ('SB','K',8,2,NULL,'RAISE',100,'RFI');
    w2do.add ('SB','Q',9,2,NULL,'RAISE',100,'RFI');
    w2do.add ('SB','J',9,4,NULL,'RAISE',100,'RFI');
    w2do.add1 ('SB','J8o',NULL,'RAISE',50,'RFI');
    w2do.add ('SB','T',8,6,NULL,'RAISE',100,'RFI');
    w2do.add ('SB','9',8,6,NULL,'RAISE',100,'RFI');
    w2do.add ('SB','8',8,5,NULL,'RAISE',100,'RFI');
    w2do.add ('SB','7',7,5,NULL,'RAISE',100,'RFI');
    w2do.add ('SB','6',6,4,NULL,'RAISE',100,'RFI');
    w2do.add ('SB','5',5,4,NULL,'RAISE',100,'RFI');
    w2do.add1 ('SB','44',NULL,'RAISE',100,'RFI');
    w2do.add1 ('SB','33',NULL,'RAISE',100,'RFI');
    
    
    
    --- vs RFI
    -- HJ vs UTG
    w2do.add_bulk ('HJ','UTG','RAISE',100,'vs RFI','aa aks aqs ajs ats a9s a5s a4s ako kk kqs kjs kts aqo qq qjs jj tt 99');
	w2do.add_bulk ('HJ','UTG','RAISE',25,'vs RFI','76s 65s 54s');   
    
    -- CO vs UTG
    w2do.add_bulk ('CO','UTG','RAISE',100,'vs RFI','aa aks aqs ajs ats a9s a5s a4s ako kk kqs kjs kts aqo qq qjs jj tt 99');
	w2do.add_bulk ('CO','UTG','RAISE',50,'vs RFI','kqo qts 88');
	w2do.add_bulk ('CO','UTG','RAISE',25,'vs RFI','76s 65s 54s'); 
    
    -- BTN vs UTG
    w2do.add_bulk ('BTN','UTG','RAISE',100,'vs RFI','aa aks kk');
	w2do.add_bulk ('BTN','UTG','RAISE',75,'vs RFI','aqs a5s a4s ako qq qjs qts');
	w2do.add_bulk ('BTN','UTG','RAISE',50,'vs RFI','a9s a8s a3s kts k9s kqo q9s ajo jj jts tt t9s');
	w2do.add_bulk ('BTN','UTG','RAISE',25,'vs RFI','ajs ats kqs kjs aqo 99 98s 88 87s 77 76s 66 64s 54s');
    
    w2do.add_bulk ('BTN','UTG','CALL',75,'vs RFI','ajs ats kqs kjs aqo 99 88 77');
    w2do.add_bulk ('BTN','UTG','CALL',50,'vs RFI','a9s a8s a3s kts k9s kqo ajo jj jts tt t9s');
    w2do.add_bulk ('BTN','UTG','CALL',25,'vs RFI','aqs a5s a4s ako qq qjs qts j9s 98s 87s 76s 65s 54s 55 44 33 22');  
    
    -- SB vs UTG
    w2do.add_bulk ('SB','UTG','RAISE',100,'vs RFI','aa aks aqs ajs ats a5s ako kk kqs kjs kts qq qjs qts jj jts tt 99');
	w2do.add_bulk ('SB','UTG','RAISE',50,'vs RFI','aqo'); 
    
    
    -- BB vs UTG
    w2do.add_bulk ('BB','UTG','RAISE',100,'vs RFI','aa aks kk qq');
	w2do.add_bulk ('BB','UTG','RAISE',75,'vs RFI','ako kqs qjs');
	w2do.add_bulk ('BB','UTG','RAISE',50,'vs RFI','aqs a5s a4s a3s kjs kts qts jts t9s 87s 76s 65s 54s');
	w2do.add_bulk ('BB','UTG','RAISE',25,'vs RFI','ajs ats a9s a8s a2s k7s k6s aqo q9s jj j9s j8s 98s');
    w2do.add_bulk ('BB','UTG','CALL',100,'vs RFI','a7s a6s k9s k8s k5s k4s k3s k2s q8s q7s q6s q5s q4s q3s ajo kjo qjo j7s ato tt t8s t7s t6s 99 97s 96s 95s 88 86s 85s 77 75s 74s 66 64s 63s 55 53s 52s 44 43s 42s 33 22');
    w2do.add1 ('BB','kqo','UTG','CALL',90,'vs RFI');
    w2do.add_bulk ('BB','UTG','CALL',75,'vs RFI','ajs ats a9s a8s a2s k7s k6s aqo q9s jj j9s j8s 98s');
    w2do.add_bulk ('BB','UTG','CALL',50,'vs RFI','aqs a5s a4s a3s kjs kts qts q2s jts j6s kto qto jto t9s 87s 76s 65s 54s');
    w2do.add_bulk ('BB','UTG','CALL',25,'vs RFI','ako kqs qjs j5s a9o t9o 84s');  
    
    --- vs RFI
    -- CO vs HJ    
    w2do.add_bulk ('CO','HJ','RAISE',100,'vs RFI','aa aks aqs ajs ats a9s a5s a4s ako kk kqs kjs kts aqo kqo qq qjs qts jj jts tt 99 88');
	w2do.add_bulk ('CO','HJ','RAISE',50,'vs RFI','ajo');
	w2do.add_bulk ('CO','HJ','RAISE',25,'vs RFI','76s 65s 54s'); 
    
    -- BTN vs HJ    
    w2do.add_bulk ('BTN','HJ','RAISE',100,'vs RFI','aa aks aqs ako kk qq');
	w2do.add_bulk ('BTN','HJ','RAISE',75,'vs RFI','a7s a5s a4s kqo qjs qts q9s');
	w2do.add_bulk ('BTN','HJ','RAISE',50,'vs RFI','ajs a9s a8s a6s a3s kts k9s aqo ajo jj jts j9s tt t9s 99 88 77');
	w2do.add_bulk ('BTN','HJ','RAISE',25,'vs RFI','ats kqs kjs 98s 87s 65s 54s 66');
	
    w2do.add_bulk ('BTN','HJ','CALL',100,'vs RFI','');
    w2do.add_bulk ('BTN','HJ','CALL',75,'vs RFI','ats kqs kjs 66');
    w2do.add_bulk ('BTN','HJ','CALL',50,'vs RFI','a9s a8s a6s a3s kts k9s aqo ajo jj jts j9s tt t9s 99 88 77');
    w2do.add_bulk ('BTN','HJ','CALL',25,'vs RFI','a7s a5s a4s kqo qjs qts q9s 98s 87s 76s 54s 44 33 22 55');  
  
    
    -- SB vs HJ
    w2do.add_bulk ('SB','HJ','RAISE',100,'vs RFI','aa aks aqs ajs ats a5s ako kk kqs kjs kts aqo qq qjs qts jj jts tt 99');
	w2do.add_bulk ('SB','HJ','RAISE',50,'vs RFI','a4s 88'); 
    
    
    -- BB vs HJ
    
    w2do.add_bulk ('BB','HJ','RAISE',100,'vs RFI','aa aks aqs ako kk qq');
	w2do.add_bulk ('BB','HJ','RAISE',75,'vs RFI','a5s kqs kjs qjs qts jj jts');
	w2do.add_bulk ('BB','HJ','RAISE',50,'vs RFI','a4s a3s kts t9s 87s 76s 65s 54s');
	w2do.add_bulk ('BB','HJ','RAISE',25,'vs RFI','ajs ats a9s a8s a2s k7s k6s aqo q9s j9s j8s tt t8s 98s');
    w2do.add_bulk ('BB','HJ','CALL',100,'vs RFI','a7s a6s k9s k8s k5s k4s k3s k2s kqo q8s q7s q6s q5s q4s q3s q2s ajo kjo qjo j7s j6s ato kto qto jto t7s t6s a9o 99 97s 96s 95s 88 86s 85s 77 75s 74s 66 64s 63s 55 53s 52s 44 43s 42s 33 22');
    w2do.add_bulk ('BB','HJ','CALL',75,'vs RFI','ajs ats a9s a8s a2s k7s k6s aqo q9s j9s j8s tt t8s 98s a5o');
    w2do.add_bulk ('BB','HJ','CALL',50,'vs RFI','a4s a3s kts t9s 87s 76s 65s 54s j5s 84s');
    w2do.add_bulk ('BB','HJ','CALL',25,'vs RFI','a5s kqs kjs qjs qts jj jts t9o a8o 98o 87o');  

    
    --- vs RFI
    -- BTN vs CO 
    w2do.add_bulk ('BTN','CO','RAISE',100,'vs RFI','aa aks aqs ako kk qq');
    w2do.add_bulk ('BTN','CO','RAISE',75,'vs RFI','a7s a6s a4s a3s k9s aqo kqo q9s ajo jj j9s');
    w2do.add_bulk ('BTN','CO','RAISE',50,'vs RFI','ajs a9s a8s kqs kjs kts k8s k7s k6s qjs qts q8s kjo jts ato tt t9s 99 88 77 66');
    w2do.add_bulk ('BTN','CO','RAISE',25,'vs RFI','ats k5s j8s t8s 98s 87s 76s 65s 54s 55');
	
    w2do.add_bulk ('BTN','CO','CALL',100,'vs RFI','');
    w2do.add_bulk ('BTN','CO','CALL',75,'vs RFI','55');
    w2do.add_bulk ('BTN','CO','CALL',50,'vs RFI','a9s a8s kqs kjs kts qjs qts jts tt t9s 99 88 77 66 ');
    w2do.add_bulk ('BTN','CO','CALL',25,'vs RFI','a7s a6s a4s a3s k9s aqo kqo q9s ajo jj j9s t8s 98s 87s 76s 65s 54s 44 33 22');   
    

    --- vs RFI
    -- SB vs CO     
    w2do.add_bulk ('SB','CO','RAISE',100,'vs RFI','AA AKs AQs AJs ATs a9s A5s AKo KK KQs KJs KTs AQo QQ QJs QTs JJ JTs TT t9s 99 88');
	w2do.add_bulk ('SB','CO','RAISE',75,'vs RFI','kqo');
    w2do.add_bulk ('SB','CO','RAISE',50,'vs RFI','A4s k9s ajo j9s 77');
    
    --- vs RFI
    -- BB vs CO 
    w2do.add_bulk ('BB','CO','RAISE',100,'vs RFI','AA AKs AQs AKo KK QQ JJ JTs');
    w2do.add_bulk ('BB','CO','RAISE',75,'vs RFI','A5s A4s KQs KJs QJs qts J9s TT T9s');
	w2do.add_bulk ('BB','CO','RAISE',50,'vs RFI','AJs A3s KTs K9s AQo Q9s J8s 87s 76s 65s 54s');
	w2do.add_bulk ('BB','CO','RAISE',25,'vs RFI','ATs A9s A8s a2s K7s K6s KQo AJo J7s T8s 99 98s 97s 86s 75s 64s');
	
	w2do.add_bulk ('BB','CO','CALL',100,'vs RFI','A7s A6s K8s K5s K4s K3s K2s Q8s Q7s Q6s Q5s Q4s Q3s Q2s KJo QJo J6s J5s J4s J3s ATo KTo QTo JTo T7s T6s A9o K9o T9o 96s 95s A8o 88 85s 77 74s 66 63s 55 53s 52s 44 43s 42s 33 22 A5o');
    w2do.add_bulk ('BB','CO','CALL',75,'vs RFI','ATs A9s A8s K7s K6s KQo AJo J7s T8s 99 98s 97s 86s 75s 64s');
    w2do.add_bulk ('BB','CO','CALL',50,'vs RFI','AJs A3s KTs K9s AQo Q9s J8s 87s 76s 65s 54s q9o j9o 98o 87o 84s 76o j2s');
    w2do.add_bulk ('BB','CO','CALL',25,'vs RFI','A5s A4s KQs KJs QJs qts J9s TT T9s');  
    
    --- vs RFI
    -- SB vs BTN 
    w2do.add_bulk ('SB','BTN','RAISE',100,'vs RFI','aa aks aqs ajs ats a9s a8s a7s a5s a4s ako kk kqs kjs kts k9s aqo kqo qq qjs qts q9s ajo jj jts j9s tt t9s t8s 99 88 77 66');
	w2do.add_bulk ('SB','BTN','RAISE',75,'vs RFI','kjo');
	w2do.add_bulk ('SB','BTN','RAISE',50,'vs RFI','55');
	w2do.add_bulk ('SB','BTN','RAISE',25,'vs RFI','ato');

    
    --- vs RFI
    -- BB vs BTN     
    w2do.add_bulk ('BB','BTN','RAISE',100,'vs RFI','AA AKs AQs AJs A5s AKo KK KQs AQo QQ JJ JTs J9s J8s TT T9s T8s');
	w2do.add_bulk ('BB','BTN','RAISE',75,'vs RFI','A4s KJs QJs QTs 99 98s 87s 76s 65s');
	w2do.add_bulk ('BB','BTN','RAISE',50,'vs RFI','ATs A3s KTs K9s KQo Q9s AJo KJo J7s ATo 88 54s');
	w2do.add_bulk ('BB','BTN','RAISE',25,'vs RFI','A9s A8s A2s K7s K6s Q8s QJo T7s T6s 97s 86s 75s 64s');
    
	w2do.add_bulk ('BB','BTN','CALL',100,'vs RFI','A7s A6s  k8s K5s K4s K3s K2s Q7s Q6s Q5s Q4s Q3s Q2s J6s J5s J4s J3s J2s KTo QTo JTo T5s T4s T3s T2s A9o J9o Q9o J9o T9o 96s 95s A8o K8o Q8o J8o T8o 98o 85s 84s A7o K7o 87o 77 74s 73s A6o K6o 76o 66 63s A5o 65o 55 53s 52s A4o 44 43s 42s A3o 33 32s 22');
    w2do.add_bulk ('BB','BTN','CALL',75,'vs RFI','A9s A8s A2s K7s K6s Q8s QJo T7s T6s 97s 86s 75s 64s');
    w2do.add_bulk ('BB','BTN','CALL',50,'vs RFI','ATs A3s KTs K9s KQo Q9s AJo KJo J7s ATo 88 54s A2o 94s 93s');
    w2do.add_bulk ('BB','BTN','CALL',25,'vs RFI','A4s KJs QJs QTs 99 98s 87s 76s 65s'); 

    --- vs RFI
    -- BB vs SB 
    w2do.add_bulk ('BB','SB','RAISE',100,'vs RFI','AA AKs AQs AJs A5s AKo KK KQs KJs AQo QQ JJ TT');
	w2do.add_bulk ('BB','SB','RAISE',75,'vs RFI','ATs KTs 99');
	w2do.add_bulk ('BB','SB','RAISE',50,'vs RFI','A4s K6s J7s J6s J5s J4s J3s J2s T9s T6s T5s T4s T3s T2s K9o K8o Q9o Q8o J9o J8o 98s 95s T8o 88 87s A7o K7o 76s A6o K6o 65s 54s A3o A2o');
	w2do.add_bulk ('BB','SB','RAISE',25,'vs RFI','A3s k9s KQo QJs QTs AJo JTs QTO JTo t7o 97o 77 A5o A4o K5o');
	
    w2do.add_bulk ('BB','SB','CALL',100,'vs RFI','A9s A8s A7s A6s A2s K8s K7s K5s k4s K3s K2s Q9s Q8s Q7s Q6s Q5s Q4s Q3s Q2s KJo QJo J9s J8s ATo KTo T8s T7s A9o A8o 97s 96s 94s 98o 86s 85s 84s 87o 75s 74s 76o 66 64s 63s 55 53s 52s 44 43s 42s 33 32s 22');
    w2do.add_bulk ('BB','SB','CALL',75,'vs RFI','A3s KQo QJs QTs AJo JTs QTO JTo 77 A5o A4o 73s');
    w2do.add_bulk ('BB','SB','CALL',50,'vs RFI','A4s K6s J7s J6s J5s J4s J3s J2s T9s T6s T5s T4s T3s T2s K9o K8o Q9o Q8o J9o J8o 98s 95s T8o 88 87s A7o K7o 76s A6o K6o 65s 54s A3o A2o 93s 65o');
    w2do.add_bulk ('BB','SB','CALL',25,'vs RFI','ATs KTs T7o 97o K5o');
    
---------------------------------------------------------------------------------------------------
    
    --- RFI vs 3-Bet 
    -- UTG vs HJ   
    w2do.add_bulk ('UTG','HJ','RAISE',100,'RFI vs 3-Bet','AA AKs KK');
	w2do.add_bulk ('UTG','HJ','RAISE',75,'RFI vs 3-Bet','AKo KQs KJs AQo QQ');
	w2do.add_bulk ('UTG','HJ','RAISE',50,'RFI vs 3-Bet','AJs A5s JJ');
	w2do.add_bulk ('UTG','HJ','RAISE',25,'RFI vs 3-Bet','ATs');
	
    w2do.add_bulk ('UTG','HJ','CALL',100,'RFI vs 3-Bet','AQs TT');
    w2do.add_bulk ('UTG','HJ','CALL',75,'RFI vs 3-Bet','');
    w2do.add_bulk ('UTG','HJ','CALL',50,'RFI vs 3-Bet','AJs A5s JJ JTs 99 88 77 66');
    w2do.add_bulk ('UTG','HJ','CALL',25,'RFI vs 3-Bet','AKo KQs KJs KTs QQ QJs T9s 98s 87s 76s 65s 55');
    
       
    
    --- RFI vs 3-Bet 
    -- UTG vs CO   
    w2do.add_bulk ('UTG','CO','RAISE',100,'RFI vs 3-Bet','AA AKs KK');
	w2do.add_bulk ('UTG','CO','RAISE',75,'RFI vs 3-Bet','AKo KQs KJs AQo QQ');
	w2do.add_bulk ('UTG','CO','RAISE',50,'RFI vs 3-Bet','AJs A5s JJ');
	w2do.add_bulk ('UTG','CO','RAISE',25,'RFI vs 3-Bet','ATs');
	
    w2do.add_bulk ('UTG','CO','CALL',100,'RFI vs 3-Bet','AQs TT');
    w2do.add_bulk ('UTG','CO','CALL',75,'RFI vs 3-Bet','');
    w2do.add_bulk ('UTG','CO','CALL',50,'RFI vs 3-Bet','AJs A5s JJ JTs 99 88 77 66');
    w2do.add_bulk ('UTG','CO','CALL',25,'RFI vs 3-Bet','AKo KQs KJs KTs QQ QJs T9s 98s 87s 76s 65s 55');
    

    --- RFI vs 3-Bet 
    -- UTG vs BTN
    w2do.add_bulk ('UTG','BTN','RAISE',100,'RFI vs 3-Bet','AA AKs KK');
	w2do.add_bulk ('UTG','BTN','RAISE',75,'RFI vs 3-Bet','AQo QQ KJs KTs');
	w2do.add_bulk ('UTG','BTN','RAISE',50,'RFI vs 3-Bet','A5s AKo QKs JJ');
	w2do.add_bulk ('UTG','BTN','RAISE',25,'RFI vs 3-Bet','AJs ATs');
	
    w2do.add_bulk ('UTG','BTN','CALL',100,'RFI vs 3-Bet','AQs TT 99 88');
    w2do.add_bulk ('UTG','BTN','CALL',75,'RFI vs 3-Bet','AJs ATs JTs');
    w2do.add_bulk ('UTG','BTN','CALL',50,'RFI vs 3-Bet','A9s A5s A4s AKo KQs QJs QTs JJ T9s 77 66 55');
    w2do.add_bulk ('UTG','BTN','CALL',25,'RFI vs 3-Bet','KJs KTs QQ 98s 87s 76s 65s');

    --- RFI vs 3-Bet 
    -- UTG vs SB
    w2do.add_bulk ('UTG','SB','RAISE',100,'RFI vs 3-Bet','aa');
	w2do.add_bulk ('UTG','SB','RAISE',75,'RFI vs 3-Bet','aks kk');
	w2do.add_bulk ('UTG','SB','RAISE',50,'RFI vs 3-Bet','a4s kjs');
	w2do.add_bulk ('UTG','SB','RAISE',25,'RFI vs 3-Bet','ajs a5s a3s ako kts k9s aqo');
    w2do.add_bulk ('UTG','SB','CALL',100,'RFI vs 3-Bet','aqs ats kqs qq qj jj tt');
    w2do.add_bulk ('UTG','SB','CALL',75,'RFI vs 3-Bet','ajs a5s kts jts ');
    w2do.add_bulk ('UTG','SB','CALL',50,'RFI vs 3-Bet','a4s kjs kts qts 99 88 77 66 55');
    w2do.add_bulk ('UTG','SB','CALL',25,'RFI vs 3-Bet','KJs KTs QQ 98s 87s 76s 65s');


    --- RFI vs 3-Bet 
    -- UTG vs BB
    w2do.add_bulk ('UTG','BB','RAISE',100,'RFI vs 3-Bet','aa');
	w2do.add_bulk ('UTG','BB','RAISE',75,'RFI vs 3-Bet','aks');
	w2do.add_bulk ('UTG','BB','RAISE',50,'RFI vs 3-Bet','a4s kk');
	w2do.add_bulk ('UTG','BB','RAISE',25,'RFI vs 3-Bet','ats a5s a3s ako kts kjs k9s');
	
    w2do.add_bulk ('UTG','BB','CALL',100,'RFI vs 3-Bet','aqs ajs kqs qq qjs jj');
    w2do.add_bulk ('UTG','BB','CALL',75,'RFI vs 3-Bet','ats a5s kjs kts tt');
    w2do.add_bulk ('UTG','BB','CALL',50,'RFI vs 3-Bet','a4s kk 99 88 77');
    w2do.add_bulk ('UTG','BB','CALL',25,'RFI vs 3-Bet','jts t9s 87s 76s 65s 66 55');
   
    
    --- RFI vs 3-Bet 
    -- HJ vs CO
    w2do.add_bulk ('HJ','CO','RAISE',100,'RFI vs 3-Bet','aa aks ako kk qq');
	w2do.add_bulk ('HJ','CO','RAISE',75,'RFI vs 3-Bet','kjs kts aqo');
	w2do.add_bulk ('HJ','CO','RAISE',50,'RFI vs 3-Bet','ajs ats a5s kqs jj');
	w2do.add_bulk ('HJ','CO','RAISE',25,'RFI vs 3-Bet','kqo qjs tt');
	
    w2do.add_bulk ('HJ','CO','CALL',100,'RFI vs 3-Bet','aqs');
    w2do.add_bulk ('HJ','CO','CALL',75,'RFI vs 3-Bet','qjs tt 99');
    w2do.add_bulk ('HJ','CO','CALL',50,'RFI vs 3-Bet','ajs ats a5s kqs jj jts 88 77 87s 76s 66 55 44 33');
    w2do.add_bulk ('HJ','CO','CALL',25,'RFI vs 3-Bet','aqo t9s 98s 65s 54s');
 


    --- RFI vs 3-Bet 
    -- HJ vs BTN
    w2do.add_bulk ('HJ','BTN','RAISE',100,'RFI vs 3-Bet','aa aks ako kk');
	w2do.add_bulk ('HJ','BTN','RAISE',75,'RFI vs 3-Bet','kts aqo');
	w2do.add_bulk ('HJ','BTN','RAISE',50,'RFI vs 3-Bet','ats a5s a4s kjs k9s jj');
	w2do.add_bulk ('HJ','BTN','RAISE',25,'RFI vs 3-Bet','kqo qjs qts jts tt');
	
    w2do.add_bulk ('HJ','BTN','CALL',100,'RFI vs 3-Bet','aqs ajs a9s kqs 99 88');
    w2do.add_bulk ('HJ','BTN','CALL',75,'RFI vs 3-Bet','qjs qts jts 77 66');
    w2do.add_bulk ('HJ','BTN','CALL',50,'RFI vs 3-Bet','ats a5s a4s kjs jj t9s 87s 76s 55 44 33');
    w2do.add_bulk ('HJ','BTN','CALL',25,'RFI vs 3-Bet','aqo 98s 54s');

    
    
    --- RFI vs 3-Bet 
    -- HJ vs SB
    w2do.add_bulk ('HJ','SB','RAISE',100,'RFI vs 3-Bet','aa aks kk');
	w2do.add_bulk ('HJ','SB','RAISE',75,'RFI vs 3-Bet','');
	w2do.add_bulk ('HJ','SB','RAISE',50,'RFI vs 3-Bet','a4s aqo');
	w2do.add_bulk ('HJ','SB','RAISE',25,'RFI vs 3-Bet','a5s a3s kts k9s k8s qq ajo jj');
	
    w2do.add_bulk ('HJ','SB','CALL',100,'RFI vs 3-Bet','aqs ajs ats kqs kjs qjs qts jts tt t9s 99');
    w2do.add_bulk ('HJ','SB','CALL',75,'RFI vs 3-Bet','a5s ako kts qq jj');
    w2do.add_bulk ('HJ','SB','CALL',50,'RFI vs 3-Bet','a4s aqo 88 87s 77 76s 66 55 44');
    w2do.add_bulk ('HJ','SB','CALL',25,'RFI vs 3-Bet','98s 65s 54s 33');



    --- RFI vs 3-Bet 
    -- HJ vs BB
    w2do.add_bulk ('HJ','BB','RAISE',100,'RFI vs 3-Bet','aa');
	w2do.add_bulk ('HJ','BB','RAISE',75,'RFI vs 3-Bet','aks kk');
	w2do.add_bulk ('HJ','BB','RAISE',50,'RFI vs 3-Bet','a4s aqo');
	w2do.add_bulk ('HJ','BB','RAISE',25,'RFI vs 3-Bet','ats a5s a3s ako kjs kts k9s k8s');
	
    w2do.add_bulk ('HJ','BB','CALL',100,'RFI vs 3-Bet', 'aqs ajs kqs qq qjs jj tt');
    w2do.add_bulk ('HJ','BB','CALL',75,'RFI vs 3-Bet','ats a5s ako kjs kts qts 99');
    w2do.add_bulk ('HJ','BB','CALL',50,'RFI vs 3-Bet','a4s jts 88 77 66');
    w2do.add_bulk ('HJ','BB','CALL',25,'RFI vs 3-Bet','kk t9s 65s 54s 55 44 33');


    --- RFI vs 3-Bet 
    -- CO vs BTN
    w2do.add_bulk ('CO','BTN','RAISE',100,'RFI vs 3-Bet','aa aks ako qq');
	w2do.add_bulk ('CO','BTN','RAISE',75,'RFI vs 3-Bet','aqo jj');
	w2do.add_bulk ('CO','BTN','RAISE',50,'RFI vs 3-Bet','a5s a4s kts k9s ajo');
	w2do.add_bulk ('CO','BTN','RAISE',25,'RFI vs 3-Bet','ats a8s kjs k7s k6s kqo qjs qts jts j9s tt t9s');
	
    w2do.add_bulk ('CO','BTN','CALL',100,'RFI vs 3-Bet','aqs ajs a9s kqs 99 88 87s 77 76s 66 65s 55 54s 44');
    w2do.add_bulk ('CO','BTN','CALL',75,'RFI vs 3-Bet','ats a8s kjs qjs jts j9s tt t9s 33 22');
    w2do.add_bulk ('CO','BTN','CALL',50,'RFI vs 3-Bet','a5s a4s kts k9s ajo');
    w2do.add_bulk ('CO','BTN','CALL',25,'RFI vs 3-Bet','a3s aqo kqo');


    --- RFI vs 3-Bet 
    -- CO vs SB
    w2do.add_bulk ('CO','SB','RAISE',100,'RFI vs 3-Bet','aa aks kk');
	w2do.add_bulk ('CO','SB','RAISE',75,'RFI vs 3-Bet','');
	w2do.add_bulk ('CO','SB','RAISE',50,'RFI vs 3-Bet','a4s ako aqo qq k5s ajo');
	w2do.add_bulk ('CO','SB','RAISE',25,'RFI vs 3-Bet','a8s a7s a3s k9s kqo jj');
    
	w2do.add_bulk ('CO','SB','CALL',100,'RFI vs 3-Bet','aqs ajs ats a9s a5s kqs kjs kts qjs qts tt jts t9s j9s 99 98s 88 87s 76s');
    w2do.add_bulk ('CO','SB','CALL',75,'RFI vs 3-Bet','jj 77 66 65s 54s');
    w2do.add_bulk ('CO','SB','CALL',50,'RFI vs 3-Bet','a4s ako kqo qq 55 44 33 22');
    w2do.add_bulk ('CO','SB','CALL',25,'RFI vs 3-Bet','k9s kqo');



    --- RFI vs 3-Bet 
    -- CO vs BB
    w2do.add_bulk ('CO','BB','RAISE',100,'RFI vs 3-Bet','aa aks kk');
	w2do.add_bulk ('CO','BB','RAISE',75,'RFI vs 3-Bet','');
	w2do.add_bulk ('CO','BB','RAISE',50,'RFI vs 3-Bet','a4s ako aqo qq k5s');
	w2do.add_bulk ('CO','BB','RAISE',25,'RFI vs 3-Bet','a8s a7s a3s k9s kqo ajo');
	
    w2do.add_bulk ('CO','BB','CALL',100,'RFI vs 3-Bet','aqs ajs ats a9s a5s kqs kjs kts qjs qts jj jts tt t9s 99');
    w2do.add_bulk ('CO','BB','CALL',75,'RFI vs 3-Bet','88 77');
    w2do.add_bulk ('CO','BB','CALL',50,'RFI vs 3-Bet','a4s ako aqo qq 66 65s 87s 76s');
    w2do.add_bulk ('CO','BB','CALL',25,'RFI vs 3-Bet','kqo k9s 55 54s 44 33 22');


    --- RFI vs 3-Bet 
    -- BTN vs SB
    w2do.add_bulk ('BTN','SB','RAISE',100,'RFI vs 3-Bet','aa aks kk qq');
	w2do.add_bulk ('BTN','SB','RAISE',75,'RFI vs 3-Bet','ako jj');
	w2do.add_bulk ('BTN','SB','RAISE',50,'RFI vs 3-Bet','ajo kjo ato k5s');
	w2do.add_bulk ('BTN','SB','RAISE',25,'RFI vs 3-Bet','a8s a7s a6s a4s a3s kqo k9s q9s q8s j8s tt 54s');
	
    w2do.add_bulk ('BTN','SB','CALL',100,'RFI vs 3-Bet','aqs ajs ats a9s kqs kjs kts aqo qjs qts jts j9s t9s t8s 99 98s 88 87s 77 76s 66 65s 55 44 33 22');
    w2do.add_bulk ('BTN','SB','CALL',75,'RFI vs 3-Bet','a8s a7s a4s a3s k9s kqo q9s j8s tt 54s');
    w2do.add_bulk ('BTN','SB','CALL',50,'RFI vs 3-Bet','k8s k7s k6s ajo 97s 86s');
    w2do.add_bulk ('BTN','SB','CALL',25,'RFI vs 3-Bet','a2s kjo jj');


    --- RFI vs 3-Bet 
    -- BTN vs BB
    w2do.add_bulk ('BTN','BB','RAISE',100,'RFI vs 3-Bet','aa aks ako kk qq');
	w2do.add_bulk ('BTN','BB','RAISE',75,'RFI vs 3-Bet','');
	w2do.add_bulk ('BTN','BB','RAISE',50,'RFI vs 3-Bet','k5s q9s jj ajo');
	w2do.add_bulk ('BTN','BB','RAISE',25,'RFI vs 3-Bet','a7s a6s a3s a2s aqo kqo j8s ato t8s');
	
    w2do.add_bulk ('BTN','BB','CALL',100,'RFI vs 3-Bet','aqs ajs ats a9s a8s a5s a4s kqs kjs kts k9s qjs qts jts j9s tt t9s 99 98s 88 87s 77 66');
    w2do.add_bulk ('BTN','BB','CALL',75,'RFI vs 3-Bet','a7s a3s aqo kqo t8s 55');
    w2do.add_bulk ('BTN','BB','CALL',50,'RFI vs 3-Bet','k8s k7s k6s q9s jj ajo 76s 65s 54s 44');
    w2do.add_bulk ('BTN','BB','CALL',25,'RFI vs 3-Bet','a6s kjo 33 22');


    --- RFI vs 3-Bet 
    -- SB vs BB
    w2do.add_bulk ('SB','BB','RAISE',100,'RFI vs 3-Bet','aa aks aqs ako kk qq jj tt');
	w2do.add_bulk ('SB','BB','RAISE',75,'RFI vs 3-Bet','aqo ajo ato');
	w2do.add_bulk ('SB','BB','RAISE',50,'RFI vs 3-Bet','a2s k5s kqo kjo');
	w2do.add_bulk ('SB','BB','RAISE',25,'RFI vs 3-Bet','a4s a3s k7s k6s j8s 98s');
	
    w2do.add_bulk ('SB','BB','CALL',100,'RFI vs 3-Bet','ajs ats a9s a8s a7s a6s a5s kqs kjs kts k9s k8s qjs qts q9s q8s jts j9s t9s t8s 99 97s 88 87s 77 76s 66 65s 55 54s 44');
    w2do.add_bulk ('SB','BB','CALL',75,'RFI vs 3-Bet','a4s a3s k7s k6s j8s 98s 33 22');
    w2do.add_bulk ('SB','BB','CALL',50,'RFI vs 3-Bet','a2s k5s kqo kjo qjo 86s 75s');
    w2do.add_bulk ('SB','BB','CALL',25,'RFI vs 3-Bet','ajo aqo kto');

        
END;
