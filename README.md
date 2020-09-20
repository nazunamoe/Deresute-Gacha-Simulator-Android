# 데레스테 가챠 시뮬레이터

## 정보
아이돌마스터 신데렐라걸즈 스타라이트 스테이지의 가챠를 시뮬레이트 하는 안드로이드 어플리케이션입니다.<br>
추가로 카드의 정보를 확인할 수 있고, 해당 카드의 일러스트를 DB사이트에서 볼 수 있습니다.

## API 정보
다크모드 UI 구현을 위한 코드는 현재 안드로이드 10 버전 이상에서만 작동될 것입니다. 
안드로이드 10 버전 이하에도 대응되도록 작업 예정입니다.

## 현재 작동되는 기능
- 단챠
- 연챠
- 카드 정보 필터링을 통한 확인 및 상세 사항 확인
- 카드 정보 화면에서 아이콘 클릭 시, <url>https://starlight.kirara.ca</url>의 해당 아이돌 페이지로 이동

## 출처
본 어플리케이션에서 사용되는 모든 이미지는 @summertriangle-dev님이 운영하는 사이트 <br>
<url>https://starlight.kirara.ca</url>에서 가져오고 있으며, 서버에 최대한 부하를 적게 주기 위하여 Glide를 이용한 이미지 캐싱을 구현해 사용합니다.<br>
또한 DB 파일은 실제 게임의 마스터 DB파일을 SQLite로 받아와 가공하여 사용하며, <br>모든 저작권은 [BANDAI NAMCO Entertainment Inc.](https://bandainamcoent.co.jp/)에 귀속됩니다

# デレステガシャシミュレーター

## 情報
アイドルマスタシンデレラガールズスタライトステージのガシャをシミュレートするアプリケーションです。<br>
カードの情報確認及びイラストに確認などができます。

## API 情報
現在ダクムードの対応はAndroid10以上での作動のみ保証します。
Android10以下のバージョンも対応予定です。

## 今作動する機能
- ガシャ１回
- ガシャ１０回
- 情報フィルタリングを通した情報の確認及び詳細の確認
- カード情報画面でアイコンをクリックして, <url>https://starlight.kirara.ca</url>の該当するアイドルのページを表示

## 著作権
本アプリケーションのイメージは @summertriangle-devのウェブサイト <br>
<url>https://starlight.kirara.ca</url>から持って来ます, サーバーへの負荷を最大限少なくするため、Glideを利用したイメージキャッシングを実現して使用します。<br>
DBファイルは実際ゲームのマスターDBファイルをSQLiteを使って加工して使用します、 <br>DBファイルデータの著作権は全て [BANDAI NAMCO Entertainment Inc.](https://bandainamcoent.co.jp/)に帰属します。

# Deresute Gacha Simulator

## Information
This application simulate gacha function on smartphone game [Idolmaster Cinderella Girls Starlight Stage]
It can check card's information and full illust on website

## API Information
I don't know dark mode working on Android 9 and below
but I will work on that soon

## Function
- Gacha
- Rensya (Gacha * 10)
- Card search and check information, and illust

## Credit
I used image on <url>https://starlight.kirara.ca</url>, a DB Site developed by @summertriangle-dev <br>
and image caching using glide is implemented to reduce load on the server as much as possible. <br>
DB files are used by receiving and processing master DB files from real games into SQLite, All right reserved to [BANDAI NAMCO Entertainment Inc.](https://bandainamcoent.co.jp/)
