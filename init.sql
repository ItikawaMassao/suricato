delete from usuario;

delete from permissao;

delete from lousa;

INSERT INTO permissao (nome) VALUES('admin');

INSERT INTO usuario (ativo, email, nome, senha, permissao_id)
              VALUES(1, 'massao@mail.com', 'massao', '$2a$12$O0jy4fsDP0Z010Qf/qcSfuCmxdzBoJborMBXf5.d.scbfwrQfg4aS', (SELECT Id FROM permissao WHERE nome = 'admin'));

insert into lousa (nome, endereco) 
     values ("Confident or Aprehansive X People or Tech", "/assets/images/capt.png"),
            ("Drop, add, keep, improve", "/assets/images/daki.png"),
            ("Future direction, Lessons learned, Accomplishments and Problem areas", "/assets/images/flap.png"),
            ("Dealing with Failure", "/assets/images/fmea.png"),
            ("Starfish", "/assets/images/starfish.png"),
            ("Glad and Sad", "/assets/images/gladSad.png"),
            ("Lessons learned", "/assets/images/lessonsLearned.png"),
            ("Liked, learned, lacked, longed for", "/assets/images/ls.png"),
            ("Open the Box", "/assets/images/openTheBox.png"),
            ("Plus, minus, interesting", "/assets/images/pmi.png"),
            ("Repeat x Avoid", "/assets/images/repeatAvoid.png"),
            ("Thumbs up, thumbs down, new ideas and recognition", "/assets/images/thumbsUpDown.png"),
            ("Worked well, kinda Worked, didn’t Work", "/assets/images/www.png"),
            ("Hot Air Balloon", "/assets/images/balloon.png"),
            ("Speed Car", "/assets/images/speedCar.png"),
            ("Sailboat", "/assets/images/boat.png");