package teamtracker



import org.junit.*
import grails.test.mixin.*

@TestFor(OpponentController)
@Mock(Opponent)
class OpponentControllerTests {


    def populateValidParams(params) {
      assert params != null
      //  Populate valid properties like...
      params["name"] = 'someValidName'
	  params["notes"] = 'someValidNote'
    }

    void testIndex() {
        controller.index()
        assert "/opponent/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.opponentInstanceList.size() == 0
        assert model.opponentInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.opponentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.opponentInstance != null
        assert view == '/opponent/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/opponent/show/1'
        assert controller.flash.message != null
        assert Opponent.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/opponent/list'


        populateValidParams(params)
        def opponent = new Opponent(params)

        assert opponent.save() != null

        params.id = opponent.id

        def model = controller.show()

        assert model.opponentInstance == opponent
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/opponent/list'


        populateValidParams(params)
        def opponent = new Opponent(params)

        assert opponent.save() != null

        params.id = opponent.id

        def model = controller.edit()

        assert model.opponentInstance == opponent
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/opponent/list'

        response.reset()


        populateValidParams(params)
        def opponent = new Opponent(params)

        assert opponent.save() != null

        // test invalid parameters in update
        params.id = opponent.id
        // add invalid values to params object
		params["name"] = null
        controller.update()

        assert view == "/opponent/edit"
        assert model.opponentInstance != null

        opponent.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/opponent/show/$opponent.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        opponent.clearErrors()

        populateValidParams(params)
        params.id = opponent.id
        params.version = -1
        controller.update()

        assert view == "/opponent/edit"
        assert model.opponentInstance != null
        assert model.opponentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/opponent/list'

        response.reset()

        populateValidParams(params)
        def opponent = new Opponent(params)

        assert opponent.save() != null
        assert Opponent.count() == 1

        params.id = opponent.id

        controller.delete()

        assert Opponent.count() == 0
        assert Opponent.get(opponent.id) == null
        assert response.redirectedUrl == '/opponent/list'
    }
}
