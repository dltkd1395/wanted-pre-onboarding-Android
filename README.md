# 원티드 프리온보딩 Android 코스 사전과제

<img src="https://img.shields.io/badge/Android Studio-3DDC84?style=fat&logo=Android Studio&logoColor=white"> <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=fat&logo=Kotlin&logoColor=white"> <img src="https://img.shields.io/badge/Retrofit2-FF9E2A?style=fat">


## 프로젝트 설명
* News API를 활용한 앱입니다.
* API 통신을 하기 위해 Retrofit2을 활용하여 뉴스 목록을 구현하였습니다.
* 뉴스 상세 내용에서 별 모양 아이콘을 클릭 시 on/off 상태가 변경되며, Room을 사용하여 on일 경우 해당 기사가 저장되고, off일 경우 해당 기사가 삭제되도록 구현하였습니다.
* 카테고리 목록은 GridLayout을 사용하여 구현하였으며, 카테고리 클릭 시 ViewModel을 통해 해당 카테고리의 뉴스 목록을 볼 수 있도록 구현하였습니다.
* Saved 목록은 DiffUitl을 사용하여 Recyclerview를 최소한의 데이터를 갱신할 수 있도록 구현하였습니다.

## 데모 영상

|Top News Tab|Categories Tab|Saved Tab|
|---|---|---|
|<img width="240" alt="2021-12-13_22-09-52" src="https://user-images.githubusercontent.com/39490416/188368292-a4637593-f28e-4de0-862c-79535e7e2f18.gif">|<img width="240" alt="2021-12-13_22-09-52" src="https://user-images.githubusercontent.com/39490416/188368368-992d6896-821b-489c-8d1b-bcbfd4593ae6.gif">|<img width="240" alt="2021-12-13_22-09-52" src="https://user-images.githubusercontent.com/39490416/188368361-9c1469f2-ba59-4d56-a0a9-ae7872758552.gif">|

## 파일 구조

# Data Layer

```bash
├── database
│   ├── BookmarkDao.kt
│   └── BookmarkDatabase.kt
├── mapper
│   └── Mapper.kt
├── network
│   ├── api
│   │   └── NewsApi.kt
│   └── model
│       └── NewsResponse.kt
├── paging
│   └── NewsPagingSource.kt
├── repository
│   ├── local
│   │   ├── BookmarkRepository.kt
│   │   └── BookmarkRepositoryImpl.kt
│   └── remote
│       ├── NewsRepository.kt
│       └── NewsRepositoryImpl.kt
└── utlis
    └── base
        └── BaseRepository.kt
```

# Domain Layer

```bash
├── model
│   ├── Category.kt
│   ├── News.kt
│   └── NewsResponse.kt
├── repository
│   ├── BookmarkRepository.kt
│   └── NewsRepository.kt
├── usecase
│   ├── CheckBoomarkUseCase.kt
│   ├── DeleteBookmarkUseCase.kt
│   ├── GetBookmarkUseCase.kt
│   ├── GetNewsRepoUseCase.kt
│   └── InsertBookmarkUseCase.kt
└── utils
    ├── ErrorType.kt
    └── RemoteErrorEmitter.kt
```
# Presenation Layer

```bash
├── base
│   ├── BaseActivity.kt
│   ├── BaseFragment.kt
│   ├── BaseHolder.kt
│   ├── BasePagingAdapter.kt
│   └── BaseViewModel.kt
├── di
│   ├── App.kt
│   ├── LocalDataSourceModule.kt
│   ├── NetworkModule.kt
│   ├── RepositoryModule.kt
│   └── UseCaseModule.kt
├── view
│   ├── MainActivity.kt
│   ├── adapter
│   │   ├── BookmarkAdapter.kt
│   │   ├── CategoryAdapter.kt
│   │   ├── NewsPagingAdapter.kt
│   │   └── bindingadapter
│   │       ├── ImageBindingAdapter.kt
│   │       └── TextBindingAdapter.kt
│   ├── bookmark
│   │   └── BookmarkFragment.kt
│   ├── category
│   │   ├── CategoryFragment.kt
│   │   └── CategoryListFragment.kt
│   └── topnews
│       ├── NewsDetailFragment.kt
│       └── TopNewsFragment.kt
├── viewmodel
│   └── MainViewModel.kt
└── widget
    ├── ActivityExtension.kt
    ├── AuthInterceptor.kt
    ├── ScreenState.kt
    ├── SingleLiveEvent.kt
    └── Utils.kt

```
