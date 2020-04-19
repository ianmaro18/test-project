using Application;
using Xunit;

namespace UnitTests
{
	public class DogUnitTests
    {
		[Fact]
		public void Talk_Test()
		{
			var sut = new Dog();
			var actual = sut.Talk();
			Assert.Equal("Woof!", actual);
		}

		[Fact]
		public void Fetch_Test()
		{
			var sut = new Dog();
			sut.Fetch();
		}
	}
}
