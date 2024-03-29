# MACAMP
카이스트 몰입캠프 2주차

## Login Page
Using facebook sdk, made a login page. If the user is already logged in, it automatically directs the user to the main app. Also, using the navigation bar, user can logout as he/she wishes.

페이스북 sdk로 로그인을 구현하였습니다. 기존에 로그인 시 다시 로그인 할 필요가 없습니다. 또한 navigation 창으로 필요시 항상 로그아웃 할 수 있습니다

## Tab 1 - Contacts

On the first tab, there is a contact list synced to the local contact on the list. Also, on need, can add contacts that take name, email, and phone number.
휴대폰에 저장되어 있는 전화번호부를 서버에 연동하였고 필요에 따라 서버에 연결된 데이터 베이스에 추가적으로 연락처를 이름, 이메일, 번호 순서로 입력하면 추가할 수 있습니다. 

## Tab 2 - UBD.gg mobile

On the second tab, there is a mobile version of UBD.gg. It expresses the total gross of movies in Korea into UBD. When the user clicks on a movie, a window with the movie's information( stea cut, genre, runtime, etc.) pops out.

UBD 지수를 표시하는 탭을 구현하였습니다. 파이썬 beautifulsoup를 이용해 네이버 영화 크롤러를 만들어, 크롤러로 가져온 정보를 db에 저장하고, 저장한 데이터를 recyclerview로 불러왔습니다. 각 항목을 클릭하면 각 영화의 세부 정보를 가져올 수 있습니다. 또한, UBD 지수에 따라 UBD 성과를 1, 50, 100 단위로 표시해주고, 예매 버튼을 클릭하면 각 영화를 예매하는 화면으로 이동할 수 있습니다.

<img width="200" img height="350" Alt text="Hello" src="https://user-images.githubusercontent.com/50355670/64773124-c47a5500-d58c-11e9-916f-f078c8c3992a.png">      <img width="200" img height="350" alt="스크린샷 2019-09-12 오후 6 31 36" src="https://user-images.githubusercontent.com/50355670/64773126-c47a5500-d58c-11e9-86a2-6c1299135ffe.png">      <img width="200" img height="350" alt="스크린샷 2019-09-12 오후 6 31 22" src="https://user-images.githubusercontent.com/50355670/64773127-c512eb80-d58c-11e9-8a6f-2477d71e2160.png">


## Tab 3 - Image Gallery

On the last tab, there is an image gallery that can get images from the local device. Also, when the user clicks each image, a bigger image pops out that support pinch to zoom.

휴대폰 갤러리 앱에서 필요한 만큼 사진을 선택해 불러올 수 있습니다. 불러온 사진은 서버의 데이터 베이스에 저장되고 갤러리에 사진을 더 추가하고 싶다면 하단에 위치한 버튼을 눌러 갤러리에 접근한 후 선택한 이미지를 데이터 베이스에 넣어주고 추가한 사진을 grid view로 보여줍니다. 사진을 한번 누르게 되면 조금 큰 사이즈의 사진을 볼 수 있습니다.
