import teamtracker.*;

class BootStrap {



	def init = { servletContext ->

		// list to hold our newly created objects
		// I added this because I didn't know how else to get the errors to
		// display in my console window.  TBD --> ask someone if there is a
		// better way
		def ourthings =[]

		println "***creating opponents"
		def opp1 = new Opponent(name:"Diamond Divas")
		def opp2 = new Opponent(name:"Chico's Bail Bonds")
		def opp3 = new Opponent(name:"Beeliners")
		def opp4 = new Opponent(name:"AZ IZ")
		def opp5 = new Opponent(name:"Teakwoods Bar")
		def opp6 = new Opponent(name:"One Bad Inning")

		//  save the transient instances
		opp1.save()
		opp2.save()
		opp3.save()
		opp4.save()
		opp5.save()
		opp6.save()


		ourthings <<opp1
		ourthings <<opp2
		ourthings <<opp3



		println "***creating game 1"
		def g1 = new Game(date:new Date('5/12/2011')  , opponent1:opp1)		
		g1.save()
		ourthings << g1

		println "***creating game 2"
		def g2 =new Game(date:new Date('5/19/2011')  , opponent1:opp2)
		ourthings << g2
		g2.save()

		println "***creating game 3"
		def g3= new Game(date:new Date('5/26/2011')  , opponent1:opp6)		
		g3.save()

		println "***creating game 4"
		def g4=  new Game(date:new Date('6/26/2011')  , opponent1:opp5)				
		g4.save()

		println "***creating game 5"
		def g5= new Game(date:new Date('6/29/2011') , opponent1:opp2)		
		g5.save()

		println "***creating game 6"
		def g6= new Game(date:new Date('9/26/2011')  , opponent1:opp4)		
		g6.save()

		println "***creating game 7"
		def g7= new  Game(date:new Date('7/29/2011' ) , opponent1:opp1)		
		g7.save()

		println "***creating game 8"
		def g8= new Game(date:new Date('8/29/2011')  , opponent1:opp3)		
		g8.save()



		println"***creating players"
		def  p1 = new Player(firstName:"Laurel", lastName:"Wagner", status:"active")
		p1.save()
		def  p2 = new Player(firstName:"Yvonne", lastName:"Moritz", status:"active")
		p2.save()
		def  p3 = new Player(firstName:"Ann", lastName:"Williamson", status:"sub")
		p3.save()
		def  p4 = new Player(firstName:"Yvonne", lastName:"Murphy", status:"retired")
		p4.save()
		println"***created players"
		ourthings <<p1
		ourthings <<p2
		ourthings <<p3
		ourthings <<p4
		
		


//		println "***udpateing player game statuses"
//
//		def pgs2 = PlayerGameStatus.findByPlayerAndGame(p2, g1)
//		pgs2.setStatus('Playing')
//		def pgs3 = PlayerGameStatus.findByPlayerAndGame(p3, g2)
//		pgs3.setStatus('NotPlaying')
//		def pgs1 = PlayerGameStatus.findByPlayerAndGame(p3, g1)
//		pgs1.setStatus('Playing')
//		def pgs4 = PlayerGameStatus.findByPlayerAndGame(p2, g3)
//		pgs2.setStatus('Playing')
//		def pgs5 = PlayerGameStatus.findByPlayerAndGame(p3, g3)
//		pgs3.setStatus('Subbing')
//		def pgs6 = PlayerGameStatus.findByPlayerAndGame(p3, g4)
//		pgs1.setStatus('Subbing')
//		pgs1.save()
//		pgs2.save()
//		pgs3.save()
//		pgs4.save()
//		pgs5.save()
//		pgs6.save()
//
//		println "***done withtatuses"
//
//
//		ourthings << pgs2; ourthings << pgs3;
			

		dumpErrors(ourthings)
		println "***bootstrap done"
	}

	// iterate through new items and save one at a time, checking for errors
	private dumpErrors(List ourthings) {
		println "ERRORS:"
		ourthings.each {
			if (!it.save()){
				it.errors.allErrors.each { error -> println "OOPPSS in it ${error}" }
			}
		}
	}
	def destroy = {
	}
}


