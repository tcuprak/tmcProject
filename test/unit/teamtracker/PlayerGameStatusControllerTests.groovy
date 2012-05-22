package teamtracker



import org.junit.*
import grails.test.mixin.*

@TestFor(PlayerGameStatusController)
@Mock([PlayerGameStatus, Game, Player])
class PlayerGameStatusControllerTests {


    def populateValidParams(params) {
      assert params != null

      // Populate valid properties like...
      params["status"] = 'Subbing'
	  params["player"] =new Player()
	  params["game"] = new Game()
    }

    void testIndex() {
        controller.index()
        assert "/playerGameStatus/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.playerGameStatusInstanceList.size() == 0
        assert model.playerGameStatusInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.playerGameStatusInstance != null
    }

    void testSave() {
        controller.save()

        assert model.playerGameStatusInstance != null
        assert view == '/playerGameStatus/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/playerGameStatus/show/1'
        assert controller.flash.message != null
        assert PlayerGameStatus.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/playerGameStatus/list'


        populateValidParams(params)
        def playerGameStatus = new PlayerGameStatus(params)

        assert playerGameStatus.save() != null

        params.id = playerGameStatus.id

        def model = controller.show()

        assert model.playerGameStatusInstance == playerGameStatus
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/playerGameStatus/list'


        populateValidParams(params)
        def playerGameStatus = new PlayerGameStatus(params)

        assert playerGameStatus.save() != null

        params.id = playerGameStatus.id

        def model = controller.edit()

        assert model.playerGameStatusInstance == playerGameStatus
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/playerGameStatus/list'

        response.reset()


        populateValidParams(params)
        def playerGameStatus = new PlayerGameStatus(params)

        assert playerGameStatus.save() != null

        // test invalid parameters in update
        params.id = playerGameStatus.id
       params["status"] = 'This is not a status'

        controller.update()

        assert view == "/playerGameStatus/edit"
        assert model.playerGameStatusInstance != null

        playerGameStatus.clearErrors()
		params["status"] = 'Subbing'
        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/playerGameStatus/show/$playerGameStatus.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        playerGameStatus.clearErrors()

        populateValidParams(params)
        params.id = playerGameStatus.id
        params.version = -1
        controller.update()

        assert view == "/playerGameStatus/edit"
        assert model.playerGameStatusInstance != null
        assert model.playerGameStatusInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/playerGameStatus/list'

        response.reset()

        populateValidParams(params)
        def playerGameStatus = new PlayerGameStatus(params)

        assert playerGameStatus.save() != null
        assert PlayerGameStatus.count() == 1

        params.id = playerGameStatus.id

        controller.delete()

        assert PlayerGameStatus.count() == 0
        assert PlayerGameStatus.get(playerGameStatus.id) == null
        assert response.redirectedUrl == '/playerGameStatus/list'
    }
}
